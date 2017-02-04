package com.Qunar.controller;

import com.Qunar.model.charCount;
import com.Qunar.model.typeCount;
import com.Qunar.util.StringAnalyzer;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

@Controller
public class analyzeString {
    @RequestMapping("/submit1.form")
    @ResponseBody
    private String submit1(@RequestParam("file") CommonsMultipartFile file) {
        StringBuilder builder = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
            builder = new StringBuilder();
            String str = null;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return generateResult(builder.toString());
    }

    @RequestMapping("/submit2.form")
    @ResponseBody
    private String submit2(String inputText) {
        return generateResult(inputText);
    }

    private String generateResult(String str) {
        StringAnalyzer.analyze(str);
        HashMap<Character, Integer> map_charCount = StringAnalyzer.getCharCount();
        HashMap<String, Integer> map_typeCount = StringAnalyzer.getTypeCount();
        return generateJson(map_charCount, map_typeCount);
    }

    private String generateJson(HashMap<Character, Integer> map_charCount, HashMap<String, Integer> map_typeCount) {
        ArrayList<charCount> charCountList = new ArrayList<charCount>();
        ArrayList<typeCount> typeCountList = new ArrayList<typeCount>();
        for (Character ch : map_charCount.keySet()) {
            charCountList.add(new charCount(ch, map_charCount.get(ch)));
        }
        for (String str : map_typeCount.keySet()) {
            typeCountList.add(new typeCount(str, map_typeCount.get(str)));
        }
        Collections.sort(charCountList, new Comparator<charCount>() {
            public int compare(charCount o1, charCount o2) {
                if (o1.getCount() < o2.getCount()) {
                    return -1;
                } else if (o1.getCount() > o2.getCount()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Collections.reverse(charCountList);
        ArrayList<charCount> temp = new ArrayList<charCount>();
        for (int i = 0; i < charCountList.size() && i < 3; i++) {
            temp.add(charCountList.get(i));
        }
        JSONArray count1 = JSONArray.fromObject(typeCountList);
        JSONArray count2 = JSONArray.fromObject(temp);
        return count1 + "###" + count2;
    }
}
