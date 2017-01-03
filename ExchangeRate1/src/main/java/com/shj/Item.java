package com.shj.ExchangeRate1;


public class Item {
	private String date;
	private String USD;
	private String EUR;
	private String HKD;

	public Item(String date, String uSD, String eUR, String hKD) {
		this.date = date;
		USD = uSD;
		EUR = eUR;
		HKD = hKD;
	}

	public String getDate() {
		return date;
	}

	public String getUSD() {
		return USD;
	}

	public String getEUR() {
		return EUR;
	}

	public String getHKD() {
		return HKD;
	}

}
