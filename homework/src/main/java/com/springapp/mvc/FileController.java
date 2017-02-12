package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(method = RequestMethod.POST)
    public String Show(String input, ModelMap model) throws UnsupportedEncodingException {
//        String content = new String(input.getBytes("iso-8859-1"), "utf-8");
//        String str = new String(getStringNoBlank(input).getBytes("iso-8859-1"), "utf-8");
//        int[] arr = new int[4];
//        String[] top_code = new String[3];
//        int[] top_num = new int[3];
//        char[] tmp = new char[2];
//        Map<String,Integer> map = new TreeMap<String, Integer>();
//        char[] s = str.toCharArray();
//        //第一次遍历获取每个字符出现的次数。
//        for(int i=0;i<s.length;){
//                if(map.containsKey(String.valueOf(s[i]))){
//                    map.put(String.valueOf(s[i]),(Integer)(map.get(String.valueOf(s[i])).intValue()+1));
//                }
//                else{
//                    map.put(String.valueOf(s[i]),(Integer)1);
//                }
//                if(s[i]>='0'&&s[i]<='9'){arr[1]++;}
//                else if((s[i]>='a'&&s[i]<='z')||(s[i]>='A'&&s[i]<='Z')){arr[0]++;}
//                else if(isChineseByBlock(s[i])){arr[2]++;}
//                else {arr[3]++;}
//            i++;
//        }
//        //第二次遍历，找出出现最多的3个字符。
//        //判断map的大小是否大于3
//        Iterator entries = map.entrySet().iterator();
//        if(map.size()<=3){
//            int i=0;
//            while (entries.hasNext()){
//                Map.Entry entry = (Map.Entry) entries.next();
//                top_code[i] = (String)entry.getKey();
//                top_num[i] = entry.getValue()==null? 0:((Integer)entry.getValue()).intValue();
//                i++;
//            }
//        }
//        else{
//            int i=0;
//            while (entries.hasNext()&&i<3){
//                Map.Entry entry = (Map.Entry) entries.next();
//                top_code[i] = (String)entry.getKey();
//                top_num[i] = entry.getValue()==null? 0:((Integer)entry.getValue()).intValue();
//                i++;
//            }
//            int min = getmininarray(top_num);
//            while (entries.hasNext()){
//                Map.Entry entry = (Map.Entry) entries.next();
//                int num = entry.getValue()==null? 0:((Integer)entry.getValue()).intValue();
//                if(top_num[min]<num){
//                    top_code[min] = (String)entry.getKey();
//                    top_num[min] = entry.getValue()==null? 0:((Integer)entry.getValue()).intValue();
//                    min = getmininarray(top_num);
//                }
//            }
//        }
//
//        model.addAttribute("content", content);
//        model.addAttribute("title", "result");
//        model.addAttribute("message", "result");
//        model.addAttribute("head", "result page");
//        model.addAttribute("english", arr[0]);
//        model.addAttribute("number", arr[1]);
//        model.addAttribute("chinese", arr[2]);
//        model.addAttribute("punctuation", arr[3]);
//        model.addAttribute("first_name", top_code[0]);
//        model.addAttribute("first_times", top_num[0]);
//        model.addAttribute("second_name", top_code[1]);
//        model.addAttribute("second_times", top_num[1]);
//        model.addAttribute("third_name", top_code[2]);
//        model.addAttribute("third_times", top_num[2]);
        return "page";
    }
    //去掉空格回车
    public String getStringNoBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    //获取最小值
    public int getmininarray(int[] arr){
        int min = 0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]<=arr[min]) min=i;
        }
        return min;
    }
    //中文判断
    public boolean isChineseByBlock(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
            return true;
        } else {
            return false;
        }
    }
}