package CharacterCounter.service;

import CharacterCounter.model.Result;
import java.util.*;

/**
 * Created by shenhaojie on 2017/1/12.
 */
public class HandleTextService {
    public static void main(String[] args) {
    }


    // 完整的判断中文汉字和符号
    public Result judge(String strName) {
        int chinese = 0;
        int english = 0;
        int figure = 0;
        int punctuation = 0;

        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                chinese++;
            } else if (isEnglish(c)) {
                english++;
            } else if (isFigure(c)) {
                figure++;
            } else {
                punctuation++;
            }

            Integer count = charCount.get(c);
            if (count == null) {
                charCount.put(c, 1);
            } else {
                charCount.put(c, count + 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(charCount.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            // 降序排序

            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Result result = new Result();
        result.setChineseCount(chinese);
        result.setEnglishCount(english);
        result.setFigureCount(figure);
        result.setPunctuationCount(punctuation);
        result.setList(list.subList(0, 3));

        return result;


    }


    private static boolean isEnglish(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            return true;
        }
        return false;
    }

    private static boolean isFigure(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }


    private static boolean isChinese(char c) {
        if ((c >= 0x4e00) && (c <= 0x9fbb)) {
            return true;
        }
        return false;
    }

}
