package com.Qunar.util;

import java.util.HashMap;

public class StringAnalyzer {

    private StringAnalyzer() {
    }

    //统计各个字符的个数
    static HashMap<Character, Integer> charCount;
    //统计各种字符的个数
    static HashMap<String, Integer> typeCount;

    public static HashMap<Character, Integer> getCharCount() {
        return charCount;
    }

    public static HashMap<String, Integer> getTypeCount() {
        return typeCount;
    }

    //英文字母 english
    //数字 number
    //中文汉字 chinese
    //中英文标点符号 punctuation
    public static void analyze(String text) {
        charCount = new HashMap<Character, Integer>();
        typeCount = new HashMap<String, Integer>();
        typeCount.put("english", 0);
        typeCount.put("number", 0);
        typeCount.put("chinese", 0);
        typeCount.put("punctuation", 0);
        char[] charArray = text.toCharArray();
        String type;
        int count;
        for (char ch : charArray) {
            countChar(ch);
            if ((type = judgeChar(ch)) != null) {
                count = typeCount.get(type);
                typeCount.put(type, count + 1);
            }
        }
    }

    private static String judgeChar(char ch) {
        if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
            return "english";
        } else if (Character.isDigit(ch)) {
            return "number";
        } else if (0x4E00 <= ch && ch <= 0x9FCC) {
            return "chinese";
        } else if (Character.isWhitespace(ch)) {
            return null;
        } else {
            return "punctuation";
        }
    }

    private static void countChar(char ch) {
        int count = 0;
        if (charCount.containsKey(ch)) {
            count = charCount.get(ch);
        }
        charCount.put(ch, count + 1);
    }
}
