package com.wordcount.disruptor;

import com.lmax.disruptor.EventHandler;
import com.wordcount.structure.WordHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**index.jsp
 * Created by admin on 2017/1/8.
 */
public class MultipartFileEventHandler{//消费者


    public List<String> onevent(String file){
        List<String> results = new ArrayList<String>();
        int len = file.length();
        WordHome wordHome = new WordHome();
        for(int i = 0;i<len;i++){
            wordHome.put(file.charAt(i),1);
        }
        results.add(wordHome.getResult());
        results.add(wordHome.getTop());
        return results;
    }

    //对数据进行处理
    public List<String> onEvent(MultipartFile multipartFile) {

        List<String> results = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "GBK"));
            String line = null;
            WordHome wordHome = new WordHome();
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            while (reader.read(charBuffer) > 0) {
                charBuffer.flip();
                int len = charBuffer.limit();
                for (int i = 0; i < len; i++) {
                    wordHome.put(charBuffer.charAt(i), 1);
                }
                charBuffer.clear();
            }
            charBuffer = null;
            System.out.println(wordHome.getResult());
            System.out.println(wordHome.getTop());
            results.add(wordHome.getResult());
            results.add(wordHome.getTop());
            results.add(wordHome.getNumber());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(reader!=null) {
                try {
                    reader.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        return results;
    }


    //对数据进行处理
    public List<String> onEvent(String text) {

        List<String> results = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            WordHome wordHome = new WordHome();
            int len = text.length();
                for (int i = 0; i < len; i++) {
                    wordHome.put(text.charAt(i), 1);
                }

            System.out.println(wordHome.getResult());
            System.out.println(wordHome.getTop());
            results.add(wordHome.getResult());
            results.add(wordHome.getTop());
            results.add(wordHome.getNumber());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(reader!=null) {
                try {
                    reader.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        return results;
    }
}
