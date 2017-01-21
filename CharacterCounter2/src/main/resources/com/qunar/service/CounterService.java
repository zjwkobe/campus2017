package com.qunar.service;

import com.qunar.model.CountResultModel;
import com.qunar.service.impl.CounterServiceImpl;
import com.qunar.utils.CharaterUtil;
import javafx.util.Pair;

import java.util.*;

/**
 * 统计服务类
 * Created by NULL on 2017/1/21.
 */
public class CounterService implements CounterServiceImpl{

    public static void main(String[] args){
        CounterService cs = new CounterService();
        cs.count("abc123+-*/我是中国人");
    }

    /**
     * 字符统计服务
     * @param text
     * @return
     */
    public CountResultModel count(String text) {

        CountResultModel crm = new CountResultModel();
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        char[] chs = text.toCharArray();

        // 遍历统计

        for(int i=0;i<chs.length;i++){

            // log(chs[i] + " -> Character.UnicodeBlock -> " + Character.UnicodeBlock.of(chs[i]));

            switch (CharaterUtil.typeOfChar(chs[i])) {
                case CharaterUtil.CHARACTER_TYPE_LETTER:
                    log(chs[i] + "-> CHARACTER_TYPE_LETTER");
                    crm.setSumOfEnglishCharacter(crm.getSumOfEnglishCharacter()+1);
                    break;
                case CharaterUtil.CHARACTER_TYPE_PUNCTUATION:
                    log(chs[i] + "-> CHARACTER_TYPE_PUNCTUATION");
                    crm.setSumOfPunctuation(crm.getSumOfPunctuation()+1);
                    break;
                case CharaterUtil.CHARACTER_TYPE_NUMBER:
                    log(chs[i] + "-> CHARACTER_TYPE_NUMBER");
                    crm.setSumOfNumber(crm.getSumOfNumber()+1);
                    break;
                case CharaterUtil.CHARACTER_TYPE_CHINESE:
                    log(chs[i] + "-> CHARACTER_TYPE_CHINESE");
                    crm.setSumOfChineseCharacter(crm.getSumOfChineseCharacter()+1);
                    break;
                default:
                    log(chs[i] + "-> Unknow Character Type!");
                    break;
            }

            if(map.containsKey(chs[i])){
                map.put(chs[i],map.get(chs[i])+1);
            }else{
                map.put(chs[i],1);
            }

        }

        // 频率排序

        List<Map.Entry<Character, Integer>> charRate =
                new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        /* 排序前
        log("排序前");
        for (int i = 0; i < charRate.size(); i++) {
            String id = charRate.get(i).toString();
            System.out.println(id);
        }
        */

        Collections.sort(charRate, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        /* 排序后
        log("排序后");
        for (int i = 0; i < charRate.size(); i++) {
            String id = charRate.get(i).toString();
            System.out.println(id);
        }
        */

        for (int i = 0; i < charRate.size(); i++) {
            switch (i) {
                case 0:
                    crm.setTop1(new Pair<Character, Integer>(charRate.get(i).getKey(),charRate.get(i).getValue()));
                    break;
                case 1:
                    crm.setTop2(new Pair<Character, Integer>(charRate.get(i).getKey(),charRate.get(i).getValue()));
                    break;
                case 2:
                    crm.setTop3(new Pair<Character, Integer>(charRate.get(i).getKey(),charRate.get(i).getValue()));
                    break;
            }
        }

        return crm;

    }

    public static void log(String message){
        System.out.println(new Date().toLocaleString() + " Message:" + message);
    }

}
