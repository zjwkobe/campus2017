package com.Qunar.model;

public class charCount {
    private char ch;
    private int count;

    public charCount() {
    }

    public charCount(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
