package com.qunar.yq.homework.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.qunar.yq.homework.entity.CharMap;
import com.qunar.yq.homework.entity.Result;
import com.qunar.yq.homework.utils.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by qiaoy.yang on 2016/12/29.
 */
@Service
public class TextService {
    private static Logger logger = LoggerFactory.getLogger("TextService");

    public Result process(String text) {
        Result re = new Result();

        if(StringUtils.isBlank(text)){
            re.setStatus(-1);
            re.setMsg("文本为空！");
            return  re;
        }
        logger.debug("Request: " + text);
        List<Character> list = new ArrayList<Character>(text.length());
        for (char ch : text.toCharArray()) {
            list.add(ch);
        }
        //处理出现次数最多的三个字符
        processTop3(re, list);
        processEnCount(re, list);
        processCHCount(re, list);
        processNumCount(re, list);
        processChOtherCount(re, list);

        return re;
    }

    /**
     * 中文标点出现的数量
     *
     * @param re
     * @param list
     */
    private void processChOtherCount(Result re, List<Character> list) {

//        final CharMatcher charMatcher = CharMatcher.anyOf(",./\\'\";:~!@#$%^&*()_+，。、？?“”：；}{《》【】（）*&……%￥#@！~");
        final Pattern pattern = Pattern.compile("^\\pP$");

        Collection<Character> collection = Collections2.filter(list, new Predicate<Character>() {
            public boolean apply(Character character) {
                if (pattern.matcher(character + "").find()) {
                    return true;
                }
                return false;
            }
        });
        logger.debug("标点符号 " + collection.size() + " ------- " + collection.toString());
        logger.info("标点符号 " + collection.size());
        re.setChOtherCount(collection.size());
    }

    /**
     * 数字出现的数量
     *
     * @param re
     * @param list
     */
    private void processNumCount(Result re, List<Character> list) {
        Collection<Character> collection = Collections2.filter(list, new Predicate<Character>() {
            public boolean apply(Character character) {
                if ('0' <= character && character <= '9') {
                    return true;
                }
                return false;
            }
        });
        logger.debug("数字 " + collection.size() + " ------- " + collection.toString());
        logger.info("数字 " + collection.size());
        re.setNumCount(collection.size());
    }

    /**
     * 汉字出现的数量
     *
     * @param re
     * @param list
     */
    private void processCHCount(Result re, List<Character> list) {
        Collection<Character> collection = Collections2.filter(list, new Predicate<Character>() {
            public boolean apply(Character character) {
                if ('\u4e00' <= character && character <= '\u9fff') {
                    return true;
                }
                return false;
            }
        });
        logger.debug("中文汉字 " + collection.size() + " ------- " + collection.toString());
        logger.info("中文汉字 " + collection.size());
        re.setChCount(collection.size());
    }

    /**
     * 英语单词的字符数量
     *
     * @param re
     * @param list
     */
    private void processEnCount(Result re, List<Character> list) {
        Collection<Character> collection = Collections2.filter(list, new Predicate<Character>() {
            public boolean apply(Character character) {
                if ('a' <= character && character <= 'z' || 'A' <= character && character <= 'Z') {
                    return true;
                }
                return false;
            }
        });
        logger.debug("英文字母 " + collection.size() + " ------- " + collection.toString());
        logger.info("英文字母 " + collection.size());
        re.setEnCount(collection.size());
    }

    /**
     * 出现次数最多的字符
     *
     * @param re
     * @param list
     */
    private void processTop3(Result re, List<Character> list) {

        CharMap charMap = new CharMap();

        for (Character ch : list) {
            charMap.set(ch);
        }

        if (MapUtils.isEmpty(charMap)) {
            return;
        }
        MapUtils mapUtils = new MapUtils();
        Map<Character, Integer> map = mapUtils.reverse().sortByValue(charMap);
        List<Map.Entry<Character, Integer>> res = mapUtils.mapToList(map);

        logger.debug("ALL:" + res.toString());

        List<Map.Entry<Character, Integer>> top3 = res.size() > 0 ? res.subList(0, 3) : res;
        logger.debug("Top3:" + top3.toString());

        re.setTop3(top3);
    }



}
