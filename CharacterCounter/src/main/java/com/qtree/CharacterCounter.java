package com.qtree;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * Created by Wang on 2016/12/19.
 */
public class CharacterCounter {


    public static class CharacterCounterHolder{
        static  CharacterCounter instance=new CharacterCounter();
    }

    public static CharacterCounter getInstance() {
        return CharacterCounterHolder.instance;
    }



    public Data count(String text)
    {
        //去掉回车空格
        text=text.replace(" ","");
        text=text.replace("\r","");
        text=text.replace("\n","");
        Data data=new Data();
        //存放字符数量
        Map<Character,Integer> map=new HashMap<Character, Integer>();
        int nums[]=new int[4];

        for (int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(ch<='z'&&ch>='a' ||ch<='Z'&&ch>='A' )
                nums[0]++;
            else if(ch<='9'&&ch>='0'){
                nums[1]++;
            }else if(ch<=0x9fbb&&ch>=0x4e00){
                //汉字的编码区间：0x4e00–0x9fbb
                nums[2]++;
            }else {
                //剩下的是中英文标点符号
                nums[3]++;
            }
            //判断是否存在
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)+1);
            }else {
                map.put(ch,1);
            }

        }
        data.setLetter(nums[0]);
        data.setNum(nums[1]);
        data.setChinese(nums[2]);
        data.setMark(nums[3]);

        //排序
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character,Integer>>(){
            public int compare(Map.Entry<Character,Integer> map1,Map.Entry<Character,Integer> map2){
                return -(map1.getValue().compareTo(map2.getValue()));
            }
        });

        String[] strs=new String[3];
        int[] strsNum=new int[3];
        int count=0;
        for(Map.Entry<Character,Integer> m:list){
            if(count==3)
                break;
            strs[count]=m.getKey()+"";
            strsNum[count]=m.getValue();
            count++;
        }
        data.setTopNum(count);
        data.setChars(strs);
        data.setCharsNum(strsNum);
        return data;
    }




    public Data count(MultipartFile file)  {
        StringBuilder sb=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new UnicodeReader(file.getInputStream(), "UTF-8"));
            String str;
            while((str = br.readLine()) != null) {
                sb.append(str);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  count(sb.toString());
    }

}
