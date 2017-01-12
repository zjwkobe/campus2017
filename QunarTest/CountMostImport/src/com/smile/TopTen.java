package com.smile;

import java.util.*;

/**
 * 将得到的字符做TOP的判断
 * 返回结果包含多个类的个数相同的情况
 */
public class TopTen {
    public HashMap<String, Integer> topTen(String str){
        Map<String,Integer> mapStr = new HashMap<>();
        String classStr[] = str.split(";");

        for(int i=0;i<classStr.length;i++){
            if(mapStr.containsKey(classStr[i])){
                int k = mapStr.get(classStr[i]);
                mapStr.remove(classStr[i]);
                mapStr.put(classStr[i],k+1);
            }
            else{
                mapStr.put(classStr[i],1);
            }
        }
        return maxImport(mapStr);

    }

    public HashMap<String,Integer> maxImport(Map<String,Integer> mapStr){
        Set<String> setKey = mapStr.keySet();
        int[] num = new int[mapStr.size()];
        int allNum=0;
        for (String s:setKey) {
            num[allNum] = mapStr.get(s);
            allNum++;
        }
        quickSort(num,1,num.length-1);
        int start = findStartEnd(num);
        HashMap<String,Integer> returnmap = new HashMap<>();
        Set<Map.Entry<String, Integer>> entryseSet = mapStr.entrySet();


        for(Map.Entry<String, Integer> entry : entryseSet){
            int valueNum = entry.getValue();

            if(num[start] <= valueNum && valueNum <= num[num.length-1]){

//                list.add(entry.getKey());
                returnmap.put(entry.getKey(),entry.getValue());
            }
        }
        return returnmap;
    }
    //快排取top10的类
    public void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int base = numbers[start];
            int temp;
            int i = start, j = end;
            do {
                while ((numbers[i] < base) && (i < end))
                    i++;
                while ((numbers[j] > base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                quickSort(numbers, start, j);
            if (end > i)
                quickSort(numbers, i, end);
        }
    }
   //find top10的范围
    private int findStartEnd(int num[]){
        int start=1;
        for(int i=num.length-2;i>0;i--){
            if(num[i] != num[i-1]){
                start++;
                if(start == 10){
                    return i;
                }
            }
        }
        return 0;
    }
}
