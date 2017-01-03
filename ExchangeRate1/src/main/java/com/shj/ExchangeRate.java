package com.shj.ExchangeRate1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExchangeRate {
	public static void main(String[] args) throws Exception {
		String html = HttpGetUtils.getHTML("http://www.chinamoney.com.cn/fe-c/historyParity.do");
		List<Item> list = analyzeHTML(html);
		ExportExcel.exportToExcel(list);
		System.out.println("ok");
	}

	private static List<Item> analyzeHTML(String html) throws ClientProtocolException, IOException {

		Document doc = Jsoup.parse(html);// 解析HTML字符串返回一个Document实现
		List<Element> links = doc.select("[class^=dreport-row]");
		int index = 0;
		String date = null;
		String USD = null;
		String EUR = null;
		String HKD;
		List<Item> list = new ArrayList<Item>();
		for (Element element : links) {
			if (!element.text().equals(element.html())) {
				index = 1;
			}
			if (index > 5) {
				continue;
			}
			if (index == 1) {
				date = element.text();
				index++;
			} else if (index == 2) {
				USD = element.text();
				index++;
			} else if (index == 3) {
				EUR = element.text();
				index++;
			} else if (index == 4) {
				index++;
			} else if (index == 5) {
				HKD = element.text();
				Item item = new Item(date, USD, EUR, HKD);
				list.add(item);
				index++;
			}
		}
		for (Item item : list) {
			System.out.println(item.getDate() + " " + item.getUSD() + " " + item.getEUR() + " " + item.getHKD());
		}
		return list;
	}

}
