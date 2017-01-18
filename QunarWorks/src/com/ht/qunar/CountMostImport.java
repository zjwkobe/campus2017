package com.ht.qunar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMostImport {
    private static int IMPORT_MAXNUM = 1;  
	static List<File> totalFiles = new ArrayList<File>();
	static List<String> contents = new ArrayList<String>();
	static int maxNumImport = 1;
	static String maxClassImport = "";//保存引入次数最多的类名
	//static List<Entry> topK = new ArrayList<Entry>();
	static List<String> topK = new ArrayList<String>();
	
	static class ImportClass{
		static Map<String,Integer> importClassesNum = new HashMap<String,Integer>();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\EclipseWorkspace\\WangYiTest\\src";
		getFiles(path);//获取该路径下的所有文件
		getDetail(totalFiles);//获取所有文件的内容（字符串表示）
		processContent(contents);//对所有内容进行正则匹配
		System.out.println("引入次数最多的类是:"+maxClassImport);
		int k = 10;
		seekTopKMaxValue(ImportClass.importClassesNum, k);
		System.out.println("引入次数最多的"+k+"个类是:");
		int index = 1;
		for(String className:topK){
			System.out.println(index++ + "  " + className);
		}
		
	}

	private static void seekTopKMaxValue(Map<String, Integer> importClassesNum,int k) {
		if(importClassesNum==null||importClassesNum.size()==0){
			return ;
		}
		Set<String> keys = importClassesNum.keySet();
		Iterator<String> itertor = keys.iterator();
		if(importClassesNum.size()<=k){			
			while(itertor.hasNext()){
				String key = itertor.next();
				topK.add(key);
			}
		}else{
			int minMaxValue = 0;
			String keyOfMinValue = null;
			itertor = keys.iterator();
			while(itertor.hasNext()){
				String key = itertor.next();
				//keyOfMinValue = keyOfTopTenMinValue(topK,importClassesNum);
				if(topK.size()<k){
					topK.add(key);				
				}else{
					if(importClassesNum.get(key)>minMaxValue){
						topK.add(key);
						topK.remove(keyOfMinValue);					
					}
				}
				keyOfMinValue = keyOfTopTenMinValue(topK,importClassesNum);
				if(keyOfMinValue != null){
					minMaxValue = ImportClass.importClassesNum.get(keyOfMinValue);
				}
				
				
			}
		}
		
	}

	private static String keyOfTopTenMinValue(List<String> topK, Map<String, Integer> importClassesNum) {
		int min = Integer.MAX_VALUE;
		String keyOfMinValue = null;
		for(String key:topK){
			if(importClassesNum.get(key)<min){
				min = importClassesNum.get(key);
				keyOfMinValue = key;
			}
		}
		return keyOfMinValue;
		
	}

	private static void processContent(List<String> contents) {
		String[] content = stringListToArray(contents);
		if(content.length==0){
			return ;
		}
	    Pattern p = Pattern.compile("(?<= ).*(?=;)"); //创建正则表达式
		for(int i=0;i<content.length;i++){
			if(content[i].contains("import")){
				Matcher matcher = p.matcher(content[i]);//创建Matcher对象
				while(matcher.find()){//content[i]的内容中找到任意位置匹配正则表达式
					if (ImportClass.importClassesNum.get(matcher.group()) == null) {  
						ImportClass.importClassesNum.put(matcher.group(), 1);  
                    } else {  
                        int num = ImportClass.importClassesNum.get(matcher.group()) + 1;  
                        if (num > IMPORT_MAXNUM) {  
                        	maxClassImport = matcher.group();  
                        }  
                        ImportClass.importClassesNum.put(matcher.group(), num);  
                    }  
//					if(ImportClass.importClassesNum.get(matcher.group())==null){
//						ImportClass.importClassesNum.put(matcher.group(),1);//抽取匹配的子字符串
//					}else{
//						int curNum = ImportClass.importClassesNum.get(matcher.group())+1;
//						if(curNum>maxNumImport){
//							maxClassImport = matcher.group();
//						}
//						ImportClass.importClassesNum.put(matcher.group(),curNum);
//					}
				}
			}
		}
		
	}

	private static String[] stringListToArray(List<String> contents) {
		if(contents==null || contents.size()==0){
			return null;
		}
		String[] arr = new String[contents.size()];
		for(int i=0;i<contents.size();i++){
			arr[i] = contents.get(i);
		}
		return arr;
	}

	private static void getDetail(List<File> totalFiles) {
		if(totalFiles==null || totalFiles.size()==0){
			return ;
		}
		for(File f:totalFiles){
			getDetail(f);
		}
		
	}

	private static void getDetail(File file) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is,"UTF-8");
			br = new BufferedReader(isr);
			String line = "";
			while((line=br.readLine())!=null){
				contents.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
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
		
	}

	private static void getFiles(String path) {
		if(path==null||path==""){
			return ;
		}
		File file = new File(path);			
		if(!file.isDirectory()){
			return ;
		}
		File[] files = file.listFiles();
		for(int i=0;i<files.length;i++){
			if(!files[i].isDirectory()){
				totalFiles.add(files[i]);
			}else{
				getFiles(files[i].toString());
			}
		}
		
	}

}
