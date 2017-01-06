package com.statics;

import com.model.Count;
import com.model.Top3;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/6.
 */
public class StaticsString {
    Count count;
    Top3 top3;
    String text;
    public  StaticsString(String text){
        this.text=text;
    }

    /*统计各类型字符出现次数*/
    public Count countCharacters(){
        count=new Count();
        int chinese=0;
        int chars=0;
        int simbol=0;
        int num=0;
        Pattern pattern1=Pattern.compile("\\d");
        Pattern pattern2=Pattern.compile("[a-zA-Z]");
        Pattern pattern3=Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher1=pattern1.matcher(text);
        Matcher matcher2=pattern2.matcher(text);
        Matcher matcher3=pattern3.matcher(text);
        while (matcher1.find())
            num++;
        while (matcher2.find())
            chars++;
        while (matcher3.find())
            chinese++;
        simbol=text.length()-num-chars-chinese;
        count.setCharacters(chars);
        count.setChineses(chinese);
        count.setNumbers(num);
        count.setOthers(simbol);
        return count;
    }
    /*统计每个字符及出现次数*/
    public Top3 countTop3(){
        top3=new Top3();
        int i;
        for(i=0;i<text.length();i++){
            if (top3.getTreeMap().containsKey(text.charAt(i)+""))
                top3.getTreeMap().put(text.charAt(i)+"",top3.getTreeMap().get(text.charAt(i)+"")+1);
            else top3.getTreeMap().put(text.charAt(i)+"",1);
        }

        return top3;
    }

    /*将每个字符及出现次数的统计结果进行排序*/
    public List<Map.Entry<String,Integer>> getTop3List(){
        List<Map.Entry<String,Integer>> list=countTop3().getList();
        return list;
    }

    /*得到出现次数排名前三的字符*/
    public String[] getTop3Char(){
        String[] strings=new String[3];
        int i=0;
        for (Map.Entry<String,Integer> entry:getTop3List()){
            if (i<3) {
                strings[i] = entry.getKey();
                i++;
            }
        }
        return strings;
    }

    /*得到出现次数排名前三的字符出现的次数*/
    public Integer[] getTop3Int(){
        Integer[] integers=new Integer[3];
        int i=0;
        for (Map.Entry<String,Integer> entry:getTop3List()){
            if (i<3) {
                integers[i] = entry.getValue();
                i++;
            }
        }
        return integers;
    }
}