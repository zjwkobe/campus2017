package com.readdy.controller.tools;/* Created by readdy on 2017/1/31.*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class CharCounter {
    private int ENCharNum = 0;
    private int DigitNum = 0;
    private int CNCharNum = 0;
    private int PunctuationNum = 0;
    private int UnknownChar = 0;
    private TreeMap<Character,Integer> tm = new TreeMap<>();
    private PriorityQueue<MapNode> aQueue = new PriorityQueue<MapNode>();

    /*public static void main(String[] args) {
        File file = new File("C:\\Users\\readdy\\Desktop\\htmls\\index.html");

        com.readdy.controller.tools.CharCounter charCounter = new com.readdy.controller.tools.CharCounter();

        charCounter.counting(file);

        charCounter.getJsonStr();
    }*/

    public void counting(String text) {
        int len = text.length();

        for (int i = 0; i < len; ++i) {
            char x = text.charAt(i);

            if ( Character.isDigit(x) ) {
                ++DigitNum;
            } else if ( CharCounter.isCNChar(x) ) {
                ++CNCharNum;
                tm.put(x, tm.getOrDefault(x, 0)+1);
            } else if ( CharCounter.isENChar(x) ) {
                ++ENCharNum;
            } else if ( CharCounter.isENPunctuation(x) || isCNPunctuation(x) ) {
                ++PunctuationNum;
            } else {
                ++UnknownChar;
            }
        }

        tm.forEach((k, v) -> {
            aQueue.add(new MapNode(k,v));
        });
    }

    /**
     * 是否英文字母
     * @param c
     * @return
     */
    public static boolean isENChar(char c) {
        int n = ((int) c);
        if ((n >= 65 && n <= 90) || (n >= 97 && n <= 122)) {
            return true;
        }
        return false;
    }

    /**
     * 是否中文汉字
     * @param c
     * @return
     */
    public static boolean isCNChar(char c) {
        int n = ((int) c);
        //       4e00          9fa5
        if (n >= 19968 && n <= 40869) {
            return true;
        }
        return false;
    }

    /**
     * 是否英文标点符号
     * @param c
     * @return
     */
    public static boolean isENPunctuation(char c) {
        int n = ((int)c);

        if ((n >= 32 &&  n <= 47)
                || (n >= 58 && n <= 64)
                || (n >= 91 && n <= 96)
                || (n >= 123 && n <= 126)) {
            return true;
        }

        return false;
    }

    /**
     * 是否中文标点符号
     * @param c
     * @return
     */
    public static boolean isCNPunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将排名前三的
     * @return
     */
    public String getJsonStr() {
        // 统计 英文字母、数字字符、
        String jsonStr = new String("{\"ENChar\":\""+ENCharNum+"\",\"DigitChar\":\""+DigitNum+"\",\"CNChar\":\""+CNCharNum+"\",\"XChar\":\""+PunctuationNum+"\"");

        //
        for (int i = 0; i < 3; ++i) {
            MapNode node = aQueue.poll();
            if (node == null) {
                jsonStr += ",\"Top" + (i + 1) + "Char\":\"\"";
                jsonStr += ",\"Top" + (i + 1) + "Num\":\"\"";
            } else {
                jsonStr += ",\"Top" + (i + 1) + "Char\":\"" + node.getChar() + "\"";
                jsonStr += ",\"Top" + (i + 1) + "Num\":\"" + node.getTimes() + "\"";
            }
        }

        jsonStr += "}";

        return jsonStr;
    }
}

