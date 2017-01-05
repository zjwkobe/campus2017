package com.qunar.ryan.hao.charactercounter.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.google.common.collect.Maps;
import com.qunar.ryan.hao.charactercounter.pojo.Result;

/**
 * 算法工具类
 * Created by ryan.hao on 16-12-30.
 */
public class AlgorithmUtils {

    /**
     * 统计一个文本中各类字符的数量
     * @param lines
     * @return
     */
    public static Result characterCount(List<String> lines) {
        Result result = new Result();
        Map<Character, Integer> charCount = Maps.newHashMapWithExpectedSize(1024);
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                // 判断英文字符
                if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
                    result.setEn(result.getEn() + 1);
                }
                // 判断数字字符
                else if ('0' <= c && c <= '9') {
                    result.setNum(result.getNum() + 1);
                }
                // 判断汉字
                else if (19968 <= c && c <= 40891) {
                    result.setCh(result.getCh() + 1);
                }
                // 判断标点符号
                else {
                    result.setSim(result.getSim() + 1);
                }
                // 统计各种字符的个数
                if (charCount.containsKey(c)) {
                    charCount.put(c, charCount.get(c) + 1);
                } else {
                    charCount.put(c, 1);
                }
            }
        }
        // 排序
        Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(final Map.Entry<Character, Integer> o1, final Map.Entry<Character, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue() == o2.getValue()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        PriorityQueue<Map.Entry<Character, Integer>> priorityQueue
                = new PriorityQueue<Map.Entry<Character, Integer>>(1024,comparator);
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            priorityQueue.add(entry);
        }
        // 只保留前三个
        for (int i = 0; i < 3 && !charCount.isEmpty(); i++) {
            Map.Entry<Character, Integer> entry = priorityQueue.poll();
            result.getTops().add(entry.getKey());
            result.getTopsValue().add(entry.getValue());
        }
        return result;
    }

}
