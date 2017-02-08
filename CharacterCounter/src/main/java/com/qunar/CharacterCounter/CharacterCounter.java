package com.qunar.CharacterCounter;




import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jk on 2016/12/24.
 */

public class CharacterCounter {
    public  String str="";
    public CharacterCounter(File file){
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String s;
            while((s = read.readLine())!= null){
                str = str+s;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public CharacterCounter(String string) throws UnsupportedEncodingException {

        str= new String(string.getBytes("ISO-8859-1"),"UTF-8");
    }
    public CharacterCounter(){}
    public Map parse(){
        Map res = new HashMap();
        String E1 = "[\u4e00-\u9fa5]";// 中文
        String E2 = "[a-zA-Z]";// 英文
        String E3 = "[0-9]";// 数字

        int chineseCount = 0;
        int englishCount = 0;
        int numberCount = 0;

        String temp;
        for (int i = 0; i < str.length(); i++)
        {
            temp = String.valueOf(str.charAt(i));
            if (temp.matches(E1))
            {
                chineseCount++;
            }
            if (temp.matches(E2))
            {
                englishCount++;
            }
            if (temp.matches(E3))
            {
                numberCount++;
            }
        }
        System.out.println(str);
        System.out.println("汉字数:" + chineseCount);
        System.out.println("英文数:" + englishCount);
        System.out.println("数字数:" + numberCount);
        System.out.println("标点符号:" + (str.length() - (chineseCount + englishCount + numberCount)));

        res.put("chineseCount",chineseCount);
        res.put("englishCount",englishCount);
        res.put("numberCount", numberCount);
        res.put("symbolCount",str.length() - (chineseCount + englishCount + numberCount));
        return res;
    }
    public Map<String,Integer> chineseMap() {
        Map<String, Integer> res = new HashMap();
        Map<String,Integer> Top = new HashMap<String, Integer>();
        String E = "[\u4e00-\u9fa5]";// 中文
        String temp;
        for (int i = 0; i < str.length(); i++) {
            temp = String.valueOf(str.charAt(i));
            if (temp.matches(E)) {
                if (res.get(temp) == null) {
                    res.put(temp, 1);
                } else {
                    Integer num = res.get(temp);
                    res.put(temp, num+1);

                }
            }

        }

        return res;
    }
    public static Object getMaxKey(Map<String, Integer> map) {
        if (map == null) return null;
        Integer MostVal = 0;
        String Mostkey = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() > MostVal) {
                MostVal = entry.getValue();
                Mostkey = entry.getKey();
            }
        }
        return Mostkey;
    }

    public  List<Object> getTop3() {
        Map<String, Integer> tempMap = this.chineseMap();
        LinkedList<Object> list = new LinkedList<Object>();
        int n = tempMap.size();
        if (n > 3) {
            for (int i = 0; i < 3; i++) {
                list.add(getMaxKey(tempMap));
                tempMap.remove(getMaxKey(tempMap));
            }
        }else {
            for (int i = 0; i < n; i++) {
                list.add(getMaxKey(tempMap));
                tempMap.remove(getMaxKey(tempMap));
            }
        }
        return list;
    }
    public Map topChinese(){
        Map res = new HashMap();
        LinkedList list = (LinkedList) getTop3();
        System.out.println(list);
        Map chinese = chineseMap();
        System.out.println(chinese);
        res.put("first",chinese.get(list.pollFirst()));
        res.put("second",chinese.get(list.pollFirst()));
        res.put("third",chinese.get(list.pollFirst()));

    return res;
    }

}
