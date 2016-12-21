package com.yuanhaolv;

import com.yuanhaolv.service.DataToExcelImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
		DataToExcelImpl dataToExcel = (DataToExcelImpl)context.getBean("dataToExcel");
		dataToExcel.generateExcel();
	}
}
