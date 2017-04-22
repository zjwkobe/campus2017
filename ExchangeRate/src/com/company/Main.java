package com.company;

import jxl.write.WriteException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, WriteException {
	// write your code here
        ExchangeRate exchangeRate = new ExchangeRate();
        System.out.println("Crawler data");
        exchangeRate.getExchageRateData();
        System.out.println("Establish Excel");
        exchangeRate.getExcel();
        System.out.println("OVER!");
    }
}
