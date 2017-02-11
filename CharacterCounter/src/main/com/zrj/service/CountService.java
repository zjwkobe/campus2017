package com.zrj.service;

import com.zrj.model.CountCharacterModel;
import com.zrj.utils.JudgeCharUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yeluo on 2/6/17.
 */
@Service
public class CountService {
    public static final int TOPNUM = 3;
    private int number = 0;
    private int english = 0;
    private int chinese = 0;
    private int punctuation = 0;

    public CountCharacterModel getCharacterCounter(String text) {
        CountCharacterModel ccm = new CountCharacterModel();
        Map<Character, Integer> map = getCountCharMap(text);
        List<Map.Entry<Character, Integer>> list = getSortedList(map);
        int index = 0;
        List<Map.Entry<Character, Integer>> topList = new ArrayList<Map.Entry<Character, Integer>>();
        for (Map.Entry<Character, Integer> entry : list) {
            index ++;
            if (index > TOPNUM) {
                break;
            }
            topList.add(entry);
        }
        ccm = new CountCharacterModel(english, number, chinese, punctuation, topList);
        return ccm;
    }

    public Map<Character, Integer> getCountCharMap(String text) {
        Map<Character, Integer> map = new TreeMap<Character, Integer>();

        for (int i = 0; i < text.length(); i ++) {
            char c = text.charAt(i);
            try {
                if (JudgeCharUtil.isSpaceChar(c)) {
                    continue;
                }
                if (JudgeCharUtil.isDigit(c)) {
                    number++;
                } else if (JudgeCharUtil.isPunctuation(c)) {
                    punctuation++;
                } else if (JudgeCharUtil.isEnglishChar(c)) {
                    english++;
                } else if (JudgeCharUtil.isChineseChar(c)) {
                    chinese++;
                }

                int cnt = map.get(c);
                map.put(c, cnt + 1);
            } catch (NullPointerException e) {
                map.put(c, 1);
            }
        }
        return map;
    }

    public List<Map.Entry<Character, Integer>> getSortedList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> t1, Map.Entry<Character, Integer> t2) {
                if (t1.getValue() > t2.getValue()) {
                    return -1;
                } else if (t1.getValue() == t2.getValue()) {
                    if (t1.getKey() >= t2.getKey()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else if (t1.getValue() < t2.getValue()) {
                    return 1;
                }
                return 0;
            }
        });

        return list;
    }

    public CountCharacterModel readFiles(MultipartFile file) {
        CountCharacterModel ccm;
        String text = getFileText(file);
        ccm = getCharacterCounter(text);
        return ccm;
    }

    private String getFileText(MultipartFile file) {
        String text = "";
        try {
            InputStream is = file.getInputStream();
            byte[] buf = new byte[20000];
            int len = is.read(buf);
            if (len <= buf.length) {
                byte[] res = new byte[len];
                for (int i = 0; i < len; i ++) {
                    res[i] = buf[i];
                }
                text = new String(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

}
