package com.qunar.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计结果实体
 * Created by NULL on 2017/1/21.
 */
public class CountResultModel {

    // 英文字母个数
    private Integer sumOfEnglishCharacter;
    // 数字个数
    private Integer sumOfNumber;
    // 汉字个数
    private Integer sumOfChineseCharacter;
    // 中英文标点符号个数
    private Integer sumOfPunctuation;

    private Pair<Character,Integer> top1;
    private Pair<Character,Integer> top2;
    private Pair<Character,Integer> top3;

    public CountResultModel() {
        sumOfEnglishCharacter = new Integer(0);
        sumOfNumber = new Integer(0);
        sumOfChineseCharacter = new Integer(0);
        sumOfPunctuation = new Integer(0);
    }

    public Integer getSumOfEnglishCharacter() {
        return sumOfEnglishCharacter;
    }

    public void setSumOfEnglishCharacter(Integer sumOfEnglishCharacter) {
        this.sumOfEnglishCharacter = sumOfEnglishCharacter;
    }

    public Integer getSumOfNumber() {
        return sumOfNumber;
    }

    public void setSumOfNumber(Integer sumOfNumber) {
        this.sumOfNumber = sumOfNumber;
    }

    public Integer getSumOfChineseCharacter() {
        return sumOfChineseCharacter;
    }

    public void setSumOfChineseCharacter(Integer sumOfChineseCharacter) {
        this.sumOfChineseCharacter = sumOfChineseCharacter;
    }

    public Integer getSumOfPunctuation() {
        return sumOfPunctuation;
    }

    public void setSumOfPunctuation(Integer sumOfPunctuation) {
        this.sumOfPunctuation = sumOfPunctuation;
    }

    public Pair<Character, Integer> getTop1() {
        return top1;
    }

    public void setTop1(Pair<Character, Integer> top1) {
        this.top1 = top1;
    }

    public Pair<Character, Integer> getTop2() {
        return top2;
    }

    public void setTop2(Pair<Character, Integer> top2) {
        this.top2 = top2;
    }

    public Pair<Character, Integer> getTop3() {
        return top3;
    }

    public void setTop3(Pair<Character, Integer> top3) {
        this.top3 = top3;
    }
}
