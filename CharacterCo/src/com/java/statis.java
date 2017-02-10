package com.java;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by GL on 2017/2/10.
 */
@Service
public class statis {
    public JSONObject getResult(String input){
        Map map = countEn(input);
        JSONObject json = JSONObject.fromObject(map);
        return json;
    }
    private Map countEn(String input) {
        Map map = new HashMap();
        int unicodeCount = 0;
        int szCount = 0;
        int zmCount = 0;
        int fhCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                szCount++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                zmCount++;
            } else if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){
                unicodeCount++;
            }else{
                if(c!= ' '){
                fhCount++;
            }}
        }
        map.put("unicodeCount", unicodeCount);
        map.put("szCount", szCount);
        map.put("zmCount", zmCount);
        map.put("fhCount", fhCount);
        map.put("most", countMost(input));
        return map;
    }
    private ArrayList countMost(String input){
        ArrayList most = new ArrayList();
        char[] charArray = input.toCharArray();
        HashMap<Character,Integer> tm = new HashMap<Character,Integer>();
        for (int x = 0; x < charArray.length ;x++ )
        {
            if(Character.toString(charArray[x]).matches("[\\u4E00-\\u9FA5]+")) {
                if (!tm.containsKey(charArray[x])) {
                    tm.put(charArray[x], 1);
                } else {
                    int count = tm.get(charArray[x]) + 1;
                    tm.put(charArray[x], count);
                }
            }
        }
        List<Map.Entry<Character, Integer>> infoIds =
                new ArrayList<Map.Entry<Character, Integer>>(tm.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        for(Map.Entry<Character, Integer> mapping:infoIds){
            most.add(mapping.getKey()+"_"+mapping.getValue());
        }
        return most;
    }

}
