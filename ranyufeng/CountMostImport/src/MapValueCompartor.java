/**
 * Created by PC on 2017/1/16.
 */

import java.util.Comparator;
import java.util.Map;
/**
 * Created by PC on 2017/1/16.
 */
class MapValueCompartor implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}