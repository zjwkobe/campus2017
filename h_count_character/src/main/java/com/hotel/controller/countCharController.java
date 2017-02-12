package com.hotel.controller;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.Map;

/**
 * Created by ruiyang.wan on 2017/2/7.
 */
@Controller
@RequestMapping("/countChar")
public class countCharController {


    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(value = "/text/{textValue:.+}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject countText(@PathVariable("textValue") String textString){
        try {
            textString = java.net.URLDecoder.decode(textString, "utf-8");
        } catch (Exception e) {
            logger.error("参数解析错误{}",e.toString());
        }
        Map<String, Map<String, Integer>> map = countChar(textString);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject;
    }

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(HttpServletRequest request, @RequestParam("fileField") MultipartFile file) throws IOException {
/*        String path = request.getSession().getServletContext().getRealPath("file");
        String fileName = file.getOriginalFilename();*/
        String textString = "";
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            logger.error("文件获取错误{}", e);
        }
        int line = 1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(targetFile), "utf-8"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        String tempString = "";
        while ((tempString = bufferedReader.readLine()) != null) {
            stringBuilder.append(tempString);
        }
        textString = stringBuilder.toString();

        Map<String, Map<String, Integer>> map = countChar(textString);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject;
    }



    private Map<String, Map<String, Integer>> countChar(String str) {
        int qtCount = 0;
        int szCount = 0;
        int zmCount = 0;
        int hzCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                szCount++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                zmCount++;
            }
        }

        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            String len = Integer.toBinaryString(c[i]);
            if (len.length() > 8)
                hzCount++;
        }

        qtCount = str.length() - szCount - zmCount - hzCount;
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("qtCount", qtCount);
        map.put("szCount", szCount);
        map.put("zmCount", zmCount);
        map.put("zwCount", hzCount);
        Map<String, Integer> mapAll = countAll(str);
        Map<String, Integer> mapIndexOfThree = IndexOfThree(mapAll);
        Map<String, Map<String, Integer>> mapResult = new HashMap<String, Map<String, Integer>>();
        mapResult.put("count", map);
        mapResult.put("max", mapIndexOfThree);
        return mapResult;
    }

    private Map<String, Integer> IndexOfThree(Map<String, Integer> map) {
        Map<String, Integer> mapIndexOfThree = new LinkedHashMap<String, Integer>();
        int i = 0;
        int size = map.size();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            i++;
            if (i == size-2 || i == size-1 || i == size) {
                mapIndexOfThree.put(entry.getKey(), entry.getValue());
            }
        }
        return mapIndexOfThree;
    }

    private Map<String, Integer> countAll(String str) {
        Map<String,Integer>  tm = new LinkedHashMap<String,Integer>();
        Set<String> st = tm.keySet();
        char[] c = str.toCharArray();
        for(int x=0; x<c.length; x++)
        {
            if(!st.contains(String.valueOf(c[x])))
            {
                tm.put(String.valueOf(c[x]), 1);
            }
            else
            {
                tm.put(String.valueOf(c[x]), tm.get(String.valueOf(c[x]))+1);
            }
        }
        return sortByValue(tm);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
