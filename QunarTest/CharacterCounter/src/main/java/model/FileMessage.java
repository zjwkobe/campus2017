package model;


import java.util.List;
import java.util.Map;

/**
 * Created by shuxin.qin on 2016/12/26.
 */
public class FileMessage {

    private Integer enCount;
    private Integer numCount;
    private Integer chCount;
    private Integer charCount;

    private String str;

    private List<ChMax> chMaxes;

    public FileMessage() {
    }

    public FileMessage(Integer enCount, Integer numCount, Integer chCount, Integer charCount ) {
        this.enCount = enCount;
        this.numCount = numCount;
        this.chCount = chCount;
        this.charCount = charCount;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getEnCount() {
        return enCount;
    }

    public void setEnCount(Integer enCount) {
        this.enCount = enCount;
    }

    public Integer getNumCount() {
        return numCount;
    }

    public void setNumCount(Integer numCount) {
        this.numCount = numCount;
    }

    public Integer getChCount() {
        return chCount;
    }

    public void setChCount(Integer chCount) {
        this.chCount = chCount;
    }

    public Integer getCharCount() {
        return charCount;
    }

    public void setCharCount(Integer charCount) {
        this.charCount = charCount;
    }

    public List<ChMax> getChMaxes() {
        return chMaxes;
    }

    public void setChMaxes(List<ChMax> chMaxes) {
        this.chMaxes = chMaxes;
    }
}
