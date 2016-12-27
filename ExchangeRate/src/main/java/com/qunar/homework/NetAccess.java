package com.qunar.homework;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by joe on 2016/11/13.
 */
public class NetAccess {
    Map<String, String> cookies = new HashMap<String, String>();
    String baseUrl = "http://www.pbc.gov.cn";

    /**
     * 仅可访问http://www.pbc.gov.cn/下的资源
     * @param url
     * @return document
     */
    public Optional<Document> access(String url) {
        //使用jsoup提供的接口访问url
        Connection connect = Jsoup.connect(url);
        Connection.Response response = null;
        try {
            response = connect.execute();
            //保存cookie
            cookies.putAll(response.cookies());
            Document document = response.parse();
            //获取script标签及内容
            Elements elements = document.getElementsByTag("script");
            //js代码的格式为 eval(..function(){}()..)
            String jsCode = elements.get(0).html();
            //去掉外层的eval（）
            jsCode = jsCode.substring(jsCode.indexOf("(") + 1, jsCode.lastIndexOf(")"));

            Optional<String> secondJS = executeFirstJS(jsCode);//执行第一段JS
            Optional<Document> document1 = executeSecondJS(secondJS.get());
            return document1;
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 执行第一段JS，用于获取第二段JS
     *
     * @param code 第一段JS
     * @return 第二段JS
     */
    private Optional<String> executeFirstJS(String code) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            //获取js执行引擎
            ScriptEngine engine = factory.getEngineByName("js");
            code = "var result = " + code + ";";
            engine.eval(code);
            String result = (String) engine.get("result");
            return Optional.of(result);
        } catch (Exception e) {
            System.err.println("第一段JS执行出错");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 执行第二段JS
     * @param code
     * @return document
     */
    private Optional<Document> executeSecondJS(String code) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            //获取js执行引擎
            ScriptEngine engine = factory.getEngineByName("js");
            //去掉自动执行过程，改为手动执行，
            code = code.substring(0, code.lastIndexOf("}") + 1);//去掉HXXTTKKLLPPP5();
            engine.eval(code);
            Invocable invocable = (Invocable) engine;
            //分析js，生成第一个cookie
            Integer template = (Integer) engine.get("template");
//            System.out.println(template);
            String wzwstemplate = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", String.valueOf(template));
//            System.out.println(wzwstemplate);
            cookies.put("wzwstemplate", wzwstemplate);//保存cookie
//        生成第二个cookie
            String confirm = (String) invocable.invokeFunction("QWERTASDFGXYSF");
//            System.out.println(confirm);
            String wzwschallenge = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", confirm);
            cookies.put("wzwschallenge", wzwschallenge);//保存cookie
//            System.out.println(wzwschallenge);
            //获取动态url
            String dynamicurl = (String) engine.get("dynamicurl");
            String newUrl = baseUrl + dynamicurl;
            Connection connection = Jsoup.connect(newUrl).cookies(this.cookies);
            Document document = connection.execute().parse();
            
            return Optional.of(document);
        } catch (Exception e) {
            System.err.println("第二段JS执行出错");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
