package com.qunar.ExchangeRate;

/**
 * Created by George on 2016-12-07.
 */

public class Rate {


    private String date;
    private int type;
    private double data;

    public Rate(String date, int type, double data){
        this.date=date;
        this.type=type;
        this.data=data;
    }

    public Rate() {
    }

    public String  getDate(){
        return date;
    }

    public int getType(){
        return type;
    }

    public double getData(){
        return data;
    }

    @Override
    public String toString() {
        return date+"  "+type+"  "+data;
    }
}

