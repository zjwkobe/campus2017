package com.output;

import java.util.*;

/**
 * 对结果处理输出
 */
public class OutResult {
    public void printResult(HashMap<String,Integer> map){
        //map的排序
        ArrayList<Map.Entry<String,Integer>> arrayList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        int num = 0;
        for (Map.Entry<String, Integer> entry : arrayList) {
            if(num == 10)
                return ;
            System.out.println(entry.getKey() + ":" + entry.getValue()+"次");
            num++;
        }

    }

}
