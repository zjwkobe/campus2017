package com.getcookie;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2017/1/6.
 */
public class GetCookie {
    public String myConnect(String path){

        BufferedReader in = null;
        try {
            String urlString = path;
            URL realURL = new URL(urlString);
            URLConnection connection = realURL.openConnection();
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
            connection.setRequestProperty("Host","www.pbc.gov.cn");

            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setRequestProperty("Referer", "http://www.pbc.gov.cn/");
            connection.setRequestProperty("Upgrade-Insecure-Requests","1");
            connection.connect();
            Map<String,List<String>> map = connection.getHeaderFields();
            StringBuffer oneresult = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                oneresult.append(line);
            }
            String result;
            result = oneresult.toString();
            //第一次请求网站返回的字符，将字符装函数拿出来赋给p
            result = result.replace("<html><head></head><body><noscript><h1><strong>请开启JavaScript并刷新该页.</strong>"
                    + "</h1></noscript><script type=\"text/javascript\">eval(", "p=");
            result = result.replace(")</script></body></html>","");
//            result = result.replace(")</script></body></html>","");
            result += ";var getP = function(){return p};getP();";
            //result是第一次要运行的js代码
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream
                    (new File("one.js"))));
            writer.println(result);
            writer.flush();
            writer.close();
            Map<String,List<String>> oneCookie = connection.getHeaderFields();
            String excuteResult = executorJSP("getP"); //第一次脚本执行结果

            String twoJSP;
            //twoJSP为第二次要运行的js代码
            twoJSP = "var window = {"+
                    "innerWidth:1920,"+
                    "screenX:-8,"+
                    "screenY:-8,"+
                    "innerHeight:909,"+
                    "screen:{height:1080,width:1920}"+
                    "};"+
                    "var document = {"+
                    "cookie:\"\","+
                    "documentElement:{clientWidth:1903,clientHeight:909},"+
                    "body:{clientWidth:1903,clientHeight:2154}"+
                    "};";
            twoJSP += excuteResult;
            twoJSP = twoJSP.replace("var cookieString = \"\";","var cookieString = \"\";var res = \"\";" );
            twoJSP = twoJSP.replace("document.cookie = cookieString;", "res+=cookieString;res+=';';");
            twoJSP = twoJSP.replace("window.location=dynamicurl;", "return res;");
            twoJSP = twoJSP.replace("}", "}\n");


            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("two.js"))));
            writer.println(twoJSP);
            writer.flush();
            writer.close();
            String cookieResult = executorJSP("HXXTTKKLLPPP5"); //第二次脚本执行结果

            cookieResult +=oneCookie.get("Set-Cookie").get(1)+oneCookie.get("Set-Cookie").get(0);
            cookieResult=cookieResult.replace(" path=/", "");
            cookieResult = cookieResult.replace(";;", ";");
            //继续用http请求会报错，所以z这里用socket请求
            Socket socket = new Socket("www.pbc.gov.cn", 80);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String[] strs = cookieResult.split(";");
            Map<String,String> params = new HashMap<String,String>();
            params.put(strs[0].split("=")[0].trim(),strs[0].split("=")[1].trim());
            params.put(strs[1].split("=")[0].trim(),strs[1].split("=")[1].trim());
            params.put(strs[2].split("=")[0].trim(),strs[2].split("=")[1].trim());
            params.put(strs[3].split("=")[0].trim(),strs[3].split("=")[1].trim());
            cookieResult = strs[2]+";"+strs[3]+";"+strs[0]+";"+strs[1];
            writer.print("GET http://www.pbc.gov.cn/ HTTP/1.1\r\n");
            writer.print("Host: www.pbc.gov.cn\r\n"
                    + "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0\r\n"
                    + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
                    + "Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n"
                    + "Referer: http://www.pbc.gov.cn/\r\n"
                    + "Connection: keep-alive\r\n"
                    + "Upgrade-Insecure-Requests: 1\r\n"
                    + "Cookie:"+cookieResult+"\r\n\r\n");
            writer.flush();

            StringBuffer buf = new StringBuffer();
            Map<String,String> other = new HashMap<String,String>();
            while((line=in.readLine())!=null){

                if(line.contains("Location")){
                    other.put("Location", line.replace("Location: ", "").trim());
                }else if(line.contains(":")){
                    if(line.contains("Set-Cookie")){
                        line = line.replace("Set-Cookie: ", "");
                        line = line.replace("; path=/", "");

                        String[] ps = line.split("=");
                        params.put(ps[0].trim(),ps[1].trim());
                    }else{
                        String[] ps = line.split(":");
                        other.put(ps[0].trim(), ps[1].trim());
                    }
                }
            }
            String cookie = "";
            for(Map.Entry<String, String> param:params.entrySet()){
                cookie +=param.getKey()+"="+param.getValue()+";";
            }

            return cookie;
        }

        catch (Exception ew){

        }
        finally {
            try {
                if(in!=null){
                    in.close();
                }

            }
            catch (Exception e){

            }
        }

        return null;
    }

    /**
     *
     * @param method 第几次执行脚本
     * @return
     * @throws Exception
     * ScriptEngineManager java执行脚本的引擎
     */
    public String executorJSP(String method) throws Exception {
        BufferedReader reader;
        //需要执行俩次jsp代码，第二次执行的代码是第一次的运行结果
        if(method.equals("getP")){
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("one.js"))));
        }
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("two.js"))));
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.eval(reader);
        Invocable inv = (Invocable)engine;
        return (String) inv.invokeFunction(method);

    }
}
