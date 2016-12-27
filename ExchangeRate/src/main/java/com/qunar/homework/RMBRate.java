package com.qunar.homework;

import java.util.Date;
import java.util.Map;

/**
 * Created by joe on 2016/11/13.
 */
public class RMBRate {
    private Map<String, Double> rate;
    private Date date;

    public Map<String, Double> getRate() {
        return rate;
    }

    public void setRate(Map<String, Double> rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RMBRate{" +
                "rate=" + rate +
                ", date=" + date +
                '}';
    }
}
