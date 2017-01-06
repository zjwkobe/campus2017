package com.model;

import java.util.*;

/**
 * Created by Administrator on 2017/1/6.
 */
public class Top3 {
    private TreeMap treeMap;  //存储统计字符及出现次数
    private List<Map.Entry<String,Integer>> list; //存储按序统计字符及出现次数
    public Top3(){
        treeMap=new TreeMap<>();
    }

    public TreeMap<String, Integer> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<String, Integer> treeMap) {
        this.treeMap = treeMap;
    }



    /*将统计结果排序，得到排序后的链表*/
    public List<Map.Entry<String,Integer>> getList(){
        list=new LinkedList<>(treeMap.entrySet());
        Collections.sort(list,new NewComparator());
        return list;
    }

    /*按要求对统计结果排序的比较器，优先比较次数，再按字典序*/
    class NewComparator implements Comparator<Map.Entry<String,Integer>>{

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue()==o2.getValue())
                return o1.getKey().compareTo(o2.getKey());
            else return o2.getValue()-o1.getValue();
        }
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }


}