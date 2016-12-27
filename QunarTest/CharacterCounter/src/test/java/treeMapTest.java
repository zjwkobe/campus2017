import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by shuxin.qin on 2016/12/27.
 */
public class treeMapTest {
    public static void main(String[] args) {

        //不指定排序器
        Map<String, Integer> treeMap1 = new HashMap<>();
        treeMap1.put("2", 1);
        treeMap1.put("b", 11);
        treeMap1.put("1", 111);
        treeMap1.put("a", 1);
        System.out.println("treeMap1="+treeMap1);

        //指定排序器
        TreeMap<String, String> treeMap2 = new TreeMap<String, String>(new Comparator<String>(){

            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeMap2.put("2", "1");
        treeMap2.put("b", "1");
        treeMap2.put("1", "1");
        treeMap2.put("a", "1");
        System.out.println("treeMap2="+treeMap2);
    }
}
