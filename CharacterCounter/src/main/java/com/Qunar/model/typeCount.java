package com.Qunar.model;

public class typeCount {
    public String type;
    public int count;

    public typeCount(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public typeCount() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
