import java.util.*;

/**
 * Created by shuxin.qin on 2016/12/27.
 */
public class treeMapTest {
    public static void main(String[] args) {

        //不指定排序器
        Map<String, Integer> treeMap1 = new HashMap<>();
        treeMap1.put("c", 1);
        treeMap1.put("b", 11);
        treeMap1.put("d", 1);
        treeMap1.put("a", 1);

        List<Map.Entry<String,Integer>> list = new ArrayList<>(treeMap1.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o2.getValue().equals(o1.getValue()))
                {
                    return o1.getKey().compareTo(o2.getKey());
                }
                else
                   return  o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<String,Integer> entry:list){
            System.out.println("key:"+entry.getKey()+",:value:"+entry.getValue());
        }
        System.out.println("treeMap1="+treeMap1);

        //指定排序器
//        TreeMap<String, String> treeMap2 = new TreeMap<String, String>(new Comparator<String>(){
//
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });
//        treeMap2.put("2", "1");
//        treeMap2.put("b", "1");
//        treeMap2.put("1", "1");
//        treeMap2.put("a", "1");
//        System.out.println("treeMap2="+treeMap2);
    }
}
