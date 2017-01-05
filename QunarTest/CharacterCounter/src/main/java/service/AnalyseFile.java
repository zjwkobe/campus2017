package service;

import model.ChMax;
import model.FileMessage;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * Created by shuxin.qin on 2016/12/26.
 */
@Service
public class AnalyseFile {

    public FileMessage analyse(String message) {

        int enCount = 0;
        int numCount = 0;
        int chCount = 0;
        int charCount = 0;
        List<ChMax> chMaxes = new ArrayList<>();

        Map<String, Integer> map = new TreeMap<>();

        int count = message.length();

        for (int i = 0; i < count; i++) {

            if (message.codePointAt(i) >= 48 && message.codePointAt(i) <= 57) {
                numCount++;
            } else if ((message.codePointAt(i) >= 97 && message.codePointAt(i) <= 122) ||
                    (message.codePointAt(i) >= 65 && message.codePointAt(i) <= 90)) {
                enCount++;
            } else if (message.codePointAt(i) >= 19968 && message.codePointAt(i) <= 40895) {
                chCount++;
                int[] codePoints = new int[1];
                codePoints[0] = message.codePointAt(i);
                String v = new String(codePoints, 0, 1);
                if (map.get(v)!=null) {
                    map.put(v, map.get(v) + 1);
                } else {
                    map.put(v, 1);
                }
            } else {
                charCount++;
            }

        }

        chMaxes = newSort(map);

        FileMessage fileMessage = new FileMessage(enCount, numCount, chCount, charCount);
        fileMessage.setChMaxes(chMaxes);
        fileMessage.setStr(message);

        return fileMessage;
    }

    private List<ChMax> newSort(Map map) {
        List<ChMax> chMaxes = new ArrayList<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().equals(o1.getValue())) {

                    String t1 = new String();
                    String t2 = new String();
                    HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
                    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
                    format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                    format.setVCharType(HanyuPinyinVCharType.WITH_V);
                    try {
                        t1 = PinyinHelper.toHanYuPinyinString(o1.getKey(), format, ",", true);
                        t2 = PinyinHelper.toHanYuPinyinString(o2.getKey(), format, ",", true);
                    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                        badHanyuPinyinOutputFormatCombination.printStackTrace();
                    }
                    return t1.compareTo(t2);
                } else
                    return o2.getValue().compareTo(o1.getValue());
            }
        });
        int no = 1;
        for (Map.Entry<String, Integer> entry : list) {
            ChMax chMax = new ChMax();
            chMax.setNo(no);
            chMax.setChvalue(entry.getKey());
            chMax.setCount(entry.getValue());

            chMaxes.add(chMax);
            no++;
            if(no==4)
                break;
        }
        ChMax chMax = new ChMax();
        chMax.setNo(null);
        chMax.setChvalue(null);
        chMax.setCount(null);

        chMaxes.add(chMax);
        chMaxes.add(chMax);
        chMaxes.add(chMax);

        return chMaxes;
    }

}
