package model;


import java.util.List;
import java.util.Map;

/**
 * Created by shuxin.qin on 2016/12/26.
 */
public class FileMessage {

    private int enCount;
    private int numCount;
    private int chCount;
    private int charCount;

    private List<String> topCh;
    private List<Integer> topCount;

    public FileMessage() {
    }

    public FileMessage(int enCount, int numCount, int chCount, int charCount ) {
        this.enCount = enCount;
        this.numCount = numCount;
        this.chCount = chCount;
        this.charCount = charCount;
    }

    public int getEnCount() {
        return enCount;
    }

    public void setEnCount(int enCount) {
        this.enCount = enCount;
    }

    public int getNumCount() {
        return numCount;
    }

    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }

    public int getChCount() {
        return chCount;
    }

    public void setChCount(int chCount) {
        this.chCount = chCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }

    public List<String> getTopCh() {
        return topCh;
    }

    public void setTopCh(List<String> topCh) {
        this.topCh = topCh;
    }

    public List<Integer> getTopCount() {
        return topCount;
    }

    public void setTopCount(List<Integer> topCount) {
        this.topCount = topCount;
    }
}
