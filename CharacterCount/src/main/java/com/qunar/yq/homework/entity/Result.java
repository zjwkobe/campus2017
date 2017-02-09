package com.qunar.yq.homework.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by qiaoy.yang on 2016/12/29.
 */
public class Result {
    private List<Map.Entry<Character,Integer>> top3;
    private long enCount;
    private long chCount;
    private long numCount;
    private long chOtherCount;
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Map.Entry<Character, Integer>> getTop3() {
        return top3;
    }

    public void setTop3(List<Map.Entry<Character, Integer>> top3) {
        this.top3 = top3;
    }

    public long getEnCount() {
        return enCount;
    }

    public void setEnCount(long enCount) {
        this.enCount = enCount;
    }

    public long getChCount() {
        return chCount;
    }

    public void setChCount(long chCount) {
        this.chCount = chCount;
    }

    public long getNumCount() {
        return numCount;
    }

    public void setNumCount(long numCount) {
        this.numCount = numCount;
    }

    public long getChOtherCount() {
        return chOtherCount;
    }

    public void setChOtherCount(long chOtherCount) {
        this.chOtherCount = chOtherCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
