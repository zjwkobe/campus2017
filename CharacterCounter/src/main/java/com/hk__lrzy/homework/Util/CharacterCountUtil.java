package com.hk__lrzy.homework.Util;

import com.hk__lrzy.homework.Model.Article;

import java.util.*;

/**
 * Created by hk__lrzy on 2017/2/11.
 */
public class CharacterCountUtil {
    ThreadLocal<Article> articleThreadLocal = new ThreadLocal<>();

    public static Article analyseContent(String content){
        Article article = new Article();
        Map<Object,Integer> map = new HashMap<>();
        char ch ;
        for (int i = 0; i < content.length();i++){
            ch = content.charAt(i);
            if (ch >= '0' && ch <= '9'){
                article.setNumber(article.getNumber()+1);
            }else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
                article.setCharacter(article.getCharacter()+1);
            }else if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")){
                article.setChinese(article.getChinese()+1);
            }else{
                article.setSymbol(article.getSymbol()+1);
            }
            if (map.containsKey(ch)){
                System.out.println(ch + "  " + map.get(ch));
                map.put(ch,map.get(ch) + 1);
            }else{
                map.put(ch,1);
            }
        }
        article.setRank(getTopN(map,3));

        return article;
    }

    public static List getTopN(Map map ,int N){
        List<Map.Entry<String,Integer>> entryList = sortMap(map);
        System.out.println(entryList);
        List mapList = new ArrayList();
        for (int i = 0; i < N;i++){
            Map index = new HashMap();
            index.put(entryList.get(i).getKey(),entryList.get(i).getValue());
            mapList.add(index);
        }
        return mapList;
    }


    public static List sortMap(Map map){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()-o1.getValue());
            }
        });
        return list;
    }
}
