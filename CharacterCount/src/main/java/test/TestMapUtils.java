package test;

import com.google.common.collect.Maps;
import com.qunar.yq.homework.entity.Result;
import com.qunar.yq.homework.service.TextService;
import com.qunar.yq.homework.utils.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qiaoy.yang on 2017/1/20.
 */
public class TestMapUtils {

    @Test
    public void testSortByKey(){
        HashMap<Character, Integer> hashMap = Maps.newHashMap();

        hashMap.put('2',9);
        hashMap.put('1',10);
        hashMap.put('3',6);
        hashMap.put('a',6);
        hashMap.put('.',6);
        hashMap.put(',',6);
        hashMap.put('c',6);
        hashMap.put('你',6);

        Map map = new MapUtils().sortByValue(hashMap);

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry =   (Map.Entry)iterator.next();
            System.out.println(entry.getKey()+"="+entry.getValue());
        }




    }
    @Test
    public void TestA(){
        TextService textService = new TextService();

        Result result = textService.process("尼塞斯的阿迪阿红的奥aaa斯卡的");
        if(result.getStatus()==0) {
            System.out.println("CH:" + result.getChCount());
            System.out.println("OCH:" + result.getChOtherCount());
            System.out.println("EN:" + result.getEnCount());
            System.out.println("NUM:" + result.getNumCount());
            System.out.println("TOP:" + result.getTop3());
        }
    }


}
