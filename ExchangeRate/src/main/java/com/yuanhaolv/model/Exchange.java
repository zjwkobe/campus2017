package com.yuanhaolv.model;

import java.util.Date;

/**
 * Created by 元浩 on 2016/12/21.
 */
public class Exchange {
    private float exchange;//汇率
    private Area area;//地区或国家
    private Date date;//时间

    public Exchange() {
    }

    public Exchange(float exchange, Area area, Date date) {
        this.exchange = exchange;
        this.area = area;
        this.date = date;
    }

    public float getExchange() {
        return exchange;
    }

    public Area getArea() {
        return area;
    }

    public Date getDate() {
        return date;
    }

    public void setExchange(float exchange) {
        this.exchange = exchange;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
