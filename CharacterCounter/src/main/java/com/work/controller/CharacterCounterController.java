package com.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/15.
 */
@Controller
@RequestMapping("/CharacterCounter")
public class CharacterCounterController {
    @RequestMapping(value = {"/",""})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/string", method = RequestMethod.POST)
    public String uploadString(@RequestParam("stringUpload") String stringUpload, Model model) {
        counter(stringUpload, model);
        return "index";
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String s;
            BufferedReader br = new BufferedReader(new InputStreamReader(fileUpload.getInputStream()));
            while((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter(stringBuilder.toString(), model);
        return "index";
    }

    private void counter(String str, Model model) {
        //英文字母
        Integer letter;
        //数字
        Integer digit;
        //中文汉字
        Integer chinese;
        //标点
        Integer punctuation;
        //频率最高的三个汉字及对应个数存储
        String[] topChinese = new String[3];
        int[] topNumber = new int[3];

        if(null == str || str.equals("")) {
            return;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();

        letter = countLetter(str);
        digit = countDigit(str);
        chinese = countChinese(str, map);
        punctuation = countPunctuation(str);

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() < o2.getValue())
                    return 1;
                if(o1.getValue().equals(o2.getValue()))
                    return 0;
                else
                    return -1;
            }
        });

        int index = 0;
        for (Map.Entry<String, Integer> top : list) {
            topChinese[index] = top.getKey();
            topNumber[index] = top.getValue();
            if(++index >= 3)
                break;
        }

        model.addAttribute("letter", letter);
        model.addAttribute("digit", digit);
        model.addAttribute("chinese", chinese);
        model.addAttribute("punctuation", punctuation);
        model.addAttribute("topChinese1", topChinese[0]);
        model.addAttribute("topNumber1", topNumber[0]);
        model.addAttribute("topChinese2", topChinese[1]);
        model.addAttribute("topNumber2", topNumber[1]);
        model.addAttribute("topChinese3", topChinese[2]);
        model.addAttribute("topNumber3", topNumber[2]);
    }
    private int countDigit(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    private int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    private int countChinese(String str, Map<String, Integer> map) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        String s;
        while(m.find()){
            s = m.group();
            count++;
            if(!map.containsKey(s)) {
                map.put(s, 1);
            }
            else {
                map.put(s, map.get(s) + 1);
            }
        }
        return count;
    }

    private int countPunctuation(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\pP\\pS\\pZ]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }
}
