package com.zrj.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeluo on 2/6/17.
 */
public class CountCharacterModel {
    private Integer englishLetterCount;
    private Integer numberCount;
    private Integer chineseCharacterCount;
    private Integer punctuationCount;
    private List<Map.Entry<Character, Integer>> topList;



    public CountCharacterModel() {
        this.englishLetterCount = 0;
        this.numberCount = 0;
        this.chineseCharacterCount = 0;
        this.punctuationCount = 0;
    }

    public CountCharacterModel(Integer englishLetterCount, Integer numberCount, Integer chineseCharacterCount, Integer punctuationCount, List<Map.Entry<Character, Integer>> list) {
        this.englishLetterCount = englishLetterCount;
        this.numberCount = numberCount;
        this.chineseCharacterCount = chineseCharacterCount;
        this.punctuationCount = punctuationCount;
        this.topList = list;
    }

    @Override
    public String toString() {
        return "CountCharacterModel{" +
                "englishLetterCount=" + englishLetterCount +
                ", numberCount=" + numberCount +
                ", chineseCharacterCount=" + chineseCharacterCount +
                ", markCount=" + punctuationCount +
                ", list=" + topList +
                '}';
    }

    public Integer getEnglishLetterCount() {
        return englishLetterCount;
    }

    public void setEnglishLetterCount(Integer englishLetterCount) {
        this.englishLetterCount = englishLetterCount;
    }

    public Integer getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(Integer numberCount) {
        this.numberCount = numberCount;
    }

    public Integer getChineseCharacterCount() {
        return chineseCharacterCount;
    }

    public void setChineseCharacterCount(Integer chineseCharacterCount) {
        this.chineseCharacterCount = chineseCharacterCount;
    }

    public Integer getPunctuationCount() {
        return punctuationCount;
    }

    public void setPunctuationCount(Integer punctuationCount) {
        this.punctuationCount = punctuationCount;
    }

    public List<Map.Entry<Character, Integer>> getTopList() {
        return topList;
    }

    public void setTopList(List<Map.Entry<Character, Integer>> topList) {
        this.topList = topList;
    }
}
