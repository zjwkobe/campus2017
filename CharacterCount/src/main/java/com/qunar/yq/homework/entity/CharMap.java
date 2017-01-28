package com.qunar.yq.homework.entity;

import java.util.*;

/**
 * Created by qiaoy.yang on 2016/12/29.
 */
public class CharMap extends HashMap<Character,Integer>{

    public void set(Character key){
        if(this.containsKey(key)){
            Integer orgValue = this.get(key);
            this.remove(key);
            this.put(key,++orgValue);
        }else{
            this.put(key,1);
        }
    }
}
