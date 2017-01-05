package com.qunar.ryan.hao.charactercounter.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan.hao on 16-12-30.
 */
public class Result implements Serializable {

    /**
     * 英文字母数量
     */
    private int en;

    /**
     * 数字数量
     */
    private int num;

    /**
     * 中文汉字数量
     */
    private int ch;

    /**
     * 中英文标点数量
     */
    private int sim;

    /**
     * 最多字符排名
     */
    private List<Character> tops;

    /**
     * 最多字符量
     */
    private List<Integer> topsValue;

    public Result() {
        tops = new ArrayList<Character>();
        topsValue = new ArrayList<Integer>();
    }

    public int getEn() {
        return en;
    }

    public void setEn(final int en) {
        this.en = en;
    }

    public int getNum() {
        return num;
    }

    public void setNum(final int num) {
        this.num = num;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(final int ch) {
        this.ch = ch;
    }

    public int getSim() {
        return sim;
    }

    public void setSim(final int sim) {
        this.sim = sim;
    }

    public List<Character> getTops() {
        return tops;
    }

    public void setTops(final List<Character> tops) {
        this.tops = tops;
    }

    public List<Integer> getTopsValue() {
        return topsValue;
    }

    public void setTopsValue(final List<Integer> topsValue) {
        this.topsValue = topsValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("en=").append(en);
        sb.append(", num=").append(num);
        sb.append(", ch=").append(ch);
        sb.append(", sim=").append(sim);
        sb.append(", tops=").append(tops);
        sb.append(", topsValue=").append(topsValue);
        sb.append('}');
        return sb.toString();
    }
}
