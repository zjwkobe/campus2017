package com.hk__lrzy.homework.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by hk__lrzy on 2017/2/11.
 */
public class Article {
    private int character;
    private int number;
    private int chinese;
    private int symbol;
    private List<Map> rank;


    public Article() {
        this.character = 0;
        this.number = 0;
        this.chinese = 0;
        this.symbol = 0;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public List<Map> getRank() {
        return rank;
    }

    public void setRank(List<Map> rank) {
        this.rank = rank;
    }
}
