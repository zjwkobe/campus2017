package edu.zhangzl.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handle {
	
	/**
	 * 使用列表存储汉字，字母，字符以及数字的个数
	 * index:
	 *   0---汉字个数
	 *   1---字母个数
	 *   2---数字个数
	 *   3---标点个数	
	 */
	private static ArrayList<Integer> count  = new ArrayList<Integer>();
	
	private static void init(){
		while(count.size()<4){
			count.add(0);
		}
	}
	
	public static List<Integer> handleString(StringBuilder stringBuilder){
		init();
		countNumber(stringBuilder.toString());
		countChinese(stringBuilder.toString());
		countLetter(stringBuilder.toString());
		countCharacters(stringBuilder.toString());
		return count;
	}
	public static void countNumber(String str) {
        int num_number = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str.trim());
        while(m.find()){
            num_number++;
        }
       count.set(2, num_number);
        
    }

    public static void countLetter(String str) {
        int num_word = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str.trim());
        while(m.find()){
            num_word++;
        }
        count.set(1, num_word);
    }

    public static void countChinese(String str) {
        int num_chineseCharacters = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str.trim());
        while(m.find()){
            num_chineseCharacters++;
        }
        count.set(0, num_chineseCharacters);
    }
    
    
    public static void countCharacters(String str){
    	String str_2 = str.trim();
    	count.set(3, str_2.length()-count.get(0)-count.get(1)-count.get(2));
    }
    
    
    public static List<Integer> handle_inputstream(InputStream in) throws IOException{
    	StringBuilder sb = new StringBuilder();
    	byte [] b = new byte[1024]; 
    	while((in.read(b))!=-1){
    		sb.append(new String(b));
    	}
    	List<Integer> list = handleString(sb);
    	return list;
    } 

}
