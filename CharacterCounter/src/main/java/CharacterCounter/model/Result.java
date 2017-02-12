package CharacterCounter.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

/**
 * Created by shenhaojie on 2017/1/12.
 */
public class Result {
    private int chineseCount;
    private int englishCount;
    private int figureCount;
    private int punctuationCount;
    private List<Map.Entry<Character,Integer>> list=new ArrayList<Map.Entry<Character,Integer>>();

    public int getChineseCount() {
        return chineseCount;
    }

    public void setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
    }

    public int getEnglishCount() {
        return englishCount;
    }

    public void setEnglishCount(int englishCount) {
        this.englishCount = englishCount;
    }

    public int getFigureCount() {
        return figureCount;
    }

    public void setFigureCount(int figureCount) {
        this.figureCount = figureCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }

    public void setPunctuationCount(int punctuationCount) {
        this.punctuationCount = punctuationCount;
    }

    public List<Map.Entry<Character, Integer>> getList() {
        return list;
    }

    public void setList(List<Map.Entry<Character, Integer>> list) {
        this.list = list;
    }
}
