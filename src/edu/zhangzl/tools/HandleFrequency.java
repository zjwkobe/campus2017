package edu.zhangzl.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import edu.zhangzl.entity.MapEntity;


public class HandleFrequency {
	
	private  ArrayList<MapEntity> list = null;
	

	public HandleFrequency() {
		super();
		// TODO Auto-generated constructor stub
		this.list=new ArrayList<MapEntity>();
	}

	public ArrayList<MapEntity> getList() {
		return list;
	}
	
	public void handleFrequency_builder(StringBuilder builder){
		int len = builder.length();	
		MapEntity me = null;
		int location = -1;
		for(int i=0;i<len;i++){
			me = new MapEntity();
			me.setStr(builder.substring(i,i+1));
			if(list.contains(me)){
				location = list.indexOf(me);
				me = list.get(location);
				list.remove(location);
				me.setSum(me.getSum()+1);
			}else{
				me.setSum(1);
			}
			list.add(me);
		}
		Collections.sort(list);
	}


	public void handleFrequency(String value) {
		// TODO Auto-generated method stub
		this.handleFrequency_builder(new StringBuilder(value));
	}

	public void handleFrequency(InputStream in) {
		// TODO Auto-generated method stub
		try {
			StringBuilder sb = new StringBuilder();
			byte [] b = new byte[1024]; 
			while((in.read(b))!=-1){
				sb.append(new String(b).trim());
			}
			this.handleFrequency_builder(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private static void compare(String s, int val) {
//		// TODO Auto-generated method stub
//		if(val>list_i.get(0)){
//			if(!list_s.get(0).equals(s)){
//				list_s.add(2,list_s.get(1));
//				list_i.add(2, list_i.get(1));
//				
//				list_s.add(1, list_s.get(0));
//				list_i.add(1, list_i.get(0));	
//			}
//			list_s.add(0, s);
//			list_i.add(0, val);
//		}else{
//			if(val>list_i.get(1)){
//				
//			}
//		}
//	}
//
//	private static void initList(StringBuilder builder) {
//		// TODO Auto-generated method stub
//		int size = 0;
//		int len = builder.length();
//		for(int i=0;i<len;i++){
//			String s = builder.substring(i,i+1);
//			if(!list_s.contains(s)){
//				list_s.add(s);
//				list_i.add(1);
//				size++;
//				if(size==3){
//					break;
//				}
//			}
//		}
//	}
}
