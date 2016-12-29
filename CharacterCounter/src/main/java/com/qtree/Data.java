package com.qtree;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wang on 2016/12/19.
 */
public class Data {

    private int letter;
    private int chinese;
    private int num;
    private int mark;
    private int topNum;
    private String[] chars;

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    private int[] charsNum;

    public void setLetter(int letter) {
        this.letter = letter;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setChars(String[] chars) {
        this.chars = chars;
    }

    public void setCharsNum(int[] charsNum) {
        this.charsNum = charsNum;
    }

    public String toJsonString(){
        String json;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("letter",letter);
        map.put("chinese",chinese);
        map.put("num",num);
        map.put("mark",mark);
        map.put("chars",chars);
        map.put("charsNum",charsNum);
        map.put("topNum",topNum);
        json= JSON.toJSONString(map);

        return json.toString();
    }
}
