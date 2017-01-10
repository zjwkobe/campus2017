package edu.zhangzl.test;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.zhangzl.tools.HandleFrequency;

public class TestHandleFrequency {

	private HandleFrequency handlefre = null;
	
	@Before
	public void init(){
		handlefre = new HandleFrequency();
	}
	
	@Test
	public void test(){
		Scanner sc =new Scanner(System.in);
		String str = sc.nextLine();
		StringBuilder builder = new StringBuilder(str);
		handlefre.handleFrequency_builder(builder);
	}
}
