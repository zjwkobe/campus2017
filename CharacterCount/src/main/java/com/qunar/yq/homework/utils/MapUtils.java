package com.qunar.yq.homework.utils;

import com.qunar.yq.homework.entity.CharMap;

import java.util.*;

/**
 * Created by qiaoy.yang on 2017/1/20.
 */
public class  MapUtils {

    private boolean asc = true;

    /**
     * 按照key进行排序，默认升序，如果需要降序。先调用reverse
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends Comparable<? super K>,V> LinkedHashMap sortByKey(Map<K, V> map){
        if(isEmpty(map)){
            return null;
        }

        List<Map.Entry<K,V>> list = new ArrayList(map.entrySet());
        LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if(o1 != null && o2 != null) {
                    if(asc) {
                        return o1.getKey().compareTo(o2.getKey());
                    }else{
                        return o2.getKey().compareTo(o1.getKey());
                    }
                }
                return 0;
            }
        });

        for (Map.Entry<K,V> entry: list){
            if(entry == null){
                continue;
            }
            result.put(entry.getKey(),entry.getValue());
        }

        return result;
    }

    /**
     * 按照value进行排序，默认升序，如果需要降序。先调用reverse
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K,V extends Comparable<? super V>>  LinkedHashMap sortByValue(Map<K,V> map){
        if(isEmpty(map)){
            return null;
        }

        List<Map.Entry<K,V>> list = new ArrayList(map.entrySet());
        LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if(o1 != null && o2 != null) {
                    if(asc) {
                        return o1.getValue().compareTo(o2.getValue());
                    }else{
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }
                return 0;
            }
        });

        for (Map.Entry<K,V> entry: list){
            if(entry == null){
                continue;
            }
            result.put(entry.getKey(),entry.getValue());
        }

        return result;
    }

    public <K,V> List<Map.Entry<K,V>> mapToList(Map<K,V> map){
        if(map == null){
            return null;
        }
        return  new ArrayList(map.entrySet());
    }

    /**
     * 判断map中是否有元素
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map){
        return map==null || map.size() ==0 ?true:false;
    }

    /**
     * 决定升序还是降序，可重复调用，不使用默认为升序
     * @return
     */
    public MapUtils reverse(){
        this.asc = !this.asc;
        return this;
    }
}
