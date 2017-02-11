/**
 * Created by PC on 2017/1/15.
 */
/**
 * Created by PC on 2017/1/15.
 */

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class CountMostImport{

    public static HashMap<String,Integer> imMap=new HashMap<String, Integer>();

    //将所有的导入的包的个数计算出来
    public static void countAll(File file){
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for(int i=0;i<files.length;i++){
                countAll(files[i]);
            }
        }else if((file.getName().contains(".java")) && (file.exists())) {
            FileReader fin = null;
            try {
                fin = new FileReader(file);
                BufferedReader bin = new BufferedReader(fin);
                String value = null;
                while (null != (value = bin.readLine())) {
                    String importLineCommentP = "\\s*import.*";
                    if((value.matches(importLineCommentP))){
                       String[] strings=value.split("\\s+|;");
                        if(imMap.containsKey(strings[1])){
                            int count=imMap.get(strings[1]);
                            count++;
                           imMap.put(strings[1],count);
                        }else{
                            imMap.put(strings[1],1);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 使用 Map按value进行排序
     * @param imMap
     * @return
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> imMap) {
        if (imMap == null || imMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String,Integer>>(
                imMap.entrySet());
        Collections.sort(entryList, new MapValueCompartor());
        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
    //对map中导入的包取出最大的十个包
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        System.out.println("输入文件夹路径");
        String path=in.next();
        File file=new File(path);//样式为"E:\\src"
        countAll(file);
        Map<String,Integer> linkedHashMap=(LinkedHashMap)sortMapByValue(imMap);
        Iterator<Map.Entry<String, Integer>> iterator=linkedHashMap.entrySet().iterator();
        int i=1;
        while((i<11)&&(i<linkedHashMap.size())){
             Map.Entry<String,Integer> iter=iterator.next();
             System.out.println(iter.getKey()+"有"+iter.getValue()+"次");
            i++;
        }

    }
}