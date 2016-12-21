package com.yuanhaolv;

import com.yuanhaolv.controller.Statistic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Scanner;

public class Start {

	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("/applicationContext.xml");
		Statistic statistic = (Statistic)context.getBean("enter");
		System.out.println("please input a Java source file's absolute path:");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		File file = new File(path);
		int lines = statistic.getLines(file);
		System.out.println("There are " + lines + " effective lines in " + path);

		sc.close();
	}
}
