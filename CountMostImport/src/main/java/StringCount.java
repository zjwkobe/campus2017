import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 2016/11/14.
 */
public class StringCount {

    List<Map> list = new ArrayList<Map>();

    @Override
    public String toString() {
        return list.toString();
    }

    public void put(String str) {
        Map map = new Map();
        map.str = str;
        map.count = 1;
        if (!list.contains(map)) {
            list.add(map);
        } else {
            Map map1 = list.get(list.indexOf(map));
            map1.count++;
        }
    }

    class Map implements Comparable<Map> {
        String str;
        int count;

        @Override
        public boolean equals(Object obj) {
            Map s = (Map) obj;
            return this.str.equals(s.str);
        }

        public int compareTo(Map o) {
            return this.count-o.count;
        }

        @Override
        public String toString() {
            return
                    "str='" + str + '\'' +
                    ", count=" + count +
                    '}';
        }
    }


}
