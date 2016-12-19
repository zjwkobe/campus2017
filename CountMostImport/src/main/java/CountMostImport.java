import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Lihaochuan on 2016/11/25.
 */
public class CountMostImport {
    public static void main(String[] args) {
        String path = "C:\\Users\\Lihaochuan\\Desktop\\RocketMQ-master\\rocketmq-broker\\src\\main\\java";
        if (args.length > 0) path = args[0];
        final Map<String,Integer> map=new HashMap<>();
        CountImport(new File(path),map);
        Set<String> set=new TreeSet<>((o1, o2) ->map.get(o1)>map.get(o2)?-1:1);
        for(String key:map.keySet()){
            set.add(key);
        }
        int k=0;
        for(String key:set){
            k++;
            System.out.println(key+":"+map.get(key));
            if(k==11) break;
        }
    }

    public static void CountImport(File file, Map<String, Integer> map) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file1 = files[i];
                if(file1.isDirectory()) CountImport(file1,map);
                else if(file1.getName().endsWith(".java")){
                    try {
                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
                        String line="";
                        while((line=bufferedReader.readLine())!=null){
                            if(line.startsWith("pub")) break;
                            if(line.trim().startsWith("import")){
                                int start=line.lastIndexOf('.')+1;
                                int end=line.lastIndexOf(';');
                                String key=line.substring(start,end).trim();
                                if(key.equals("*")) continue;
                                Integer count=map.get(key);
                                if(count==null) map.put(key,1);
                                else map.put(key,count+1);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
