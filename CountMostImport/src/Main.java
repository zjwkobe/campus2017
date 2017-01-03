import java.io.*;
import java.util.*;

/**
 * Created by SDD on 2017/1/1.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        File file=new File("/Users/SDD/WorkSpace_java/CountMostImport/file/");
        File[] files=file.listFiles();
        Map<String,Integer> map=new HashMap<String,Integer>();
        for (File file1:files){
            //System.out.println(file1.toString());
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file1));
            String line=bufferedReader.readLine();
            while (line!=null){
                line=line.replaceAll(" ", "");//去掉所有空格
                if (line.startsWith("import")){//判断是否是import语句
                    line=line.substring(6,line.length()-1);
                    if(map.containsKey(line)){
                        map.put(line,map.get(line)+1);//计数+1
                    }else{
                        map.put(line,1);
                    }
                    //System.out.println(line);
                }
                line=bufferedReader.readLine();

            }
        }
        //System.out.println("unsorted map:"+map);
        ValueComparator vc=new ValueComparator(map);
        TreeMap<String,Integer> sorted_map=new TreeMap<String,Integer>(vc);
        sorted_map.putAll(map);
        //System.out.println("sorted map:" + sorted_map);
        Set<Map.Entry<String,Integer>> keySet = sorted_map.entrySet();
        Iterator<Map.Entry<String,Integer>> iter = keySet.iterator();
        int i=10;
        while (iter.hasNext()&&i>0)
        {
            Map.Entry<String,Integer> entry=iter.next();
            System.out.println((String)entry.getKey()+":"+entry.getValue());
            i--;
        }
    }

}
class ValueComparator implements Comparator<String> {
    Map <String,Integer> map;
    public ValueComparator(Map<String,Integer> map){
        this.map=map;
    }
    @Override
    public int compare(String o1, String o2) {//降序排序
        if (map.get(o1)>map.get(o2)){
            return -1;
        }
        else{
            return 1;
        }

    }
}
