package com.ht.qunar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {
	static int totalCount = 0;
	static int blankCount = 0;
	static int commentCount = 0;
	static int effectiveCount = 0;
	
	public static void main(String[] args) {
		String path = "D:\\EclipseWorkspace\\QunarWorks\\src\\com\\ht\\qunar\\TestFile.java";
		File file = new File(path);		
		countEffectiveLines(file);
		effectiveCount = totalCount - commentCount - blankCount;
		System.out.println("该.java文件的有效行数为："+effectiveCount+"行.");

	}

	private static int countEffectiveLines(File file) {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			while((line=br.readLine())!=null){
				totalCount++;
				countBlackLines(line);
				countCommentLines(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	private static void countCommentLines(String line) {
		// TODO Auto-generated method stub
		if(line.matches("\\s*/\\*{1,}.*(\\*/).*")){
			commentCount++;
	     }
	}

	private static void countBlackLines(String line) {
		// TODO Auto-generated method stub
		if(line.matches("^[\\s&&[^\\n]]*$")){
			blankCount++;
		}
	}

}
