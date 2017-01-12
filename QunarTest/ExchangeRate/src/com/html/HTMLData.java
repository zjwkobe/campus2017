package com.html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2017/1/8.
 */
public class HTMLData {
    public String getData(String url, String cookie) {

        try {
            URL realURL = new URL(url);
            URLConnection connection = realURL.openConnection();
            BufferedReader in = null;
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
            connection.setRequestProperty("Host", "www.pbc.gov.cn");
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");

            connection.setRequestProperty("Referer", "http://www.pbc.gov.cn/");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            StringBuffer result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

        } catch (Exception e) {

        }


        return null;


    }
}
