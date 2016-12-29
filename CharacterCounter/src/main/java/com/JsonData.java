package com;

/**
 * @author Lihaochuan
 * @description
 */
public class JsonData {
    private int chinese;
    private int english;
    private int number;
    private int punctuation;
    private String[][] top;
    private boolean success=true;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public String[][] getTop() {
        return top;
    }

    public void setTop(String[][] top) {
        this.top = top;
    }
}
