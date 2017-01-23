package Java.CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hk__lrzy on 2017/1/14.
 * target：根据指定项目目录下（可以认为是java源文件目录）中，统计被import最多的类，前十个是什么。
 */
public class Analyze {

    private static Map<String,Integer> records= new HashMap<>();
    private static int INIT_RECORDS_NUMBER = 1;

    /**
     * 遍历提供的root目录，可以利用treemap等
     * @param root 根目录
     * @return List集合
     */
    public void execute(String root) {
        this.analyzePath(new File(root));
        System.out.println(records);
        //return new ArrayList();
    }

    protected void analyzePath(File src){
        if (src.exists()){
            if (src.isFile()){
                this.countFile(src);
            }else if (src.isDirectory()) {
                File[] listFile = src.listFiles();
                for (File file : listFile) {
                    if (file.isFile()) {
                        this.countFile(file);
                    } else if (file.isDirectory()) {
                        this.analyzePath(file);
                    }
                }
            }
        }
    }

    protected void countFile(File file){
        int suffix = file.getName().lastIndexOf(".");
        if (suffix != -1 && file.getName().substring(suffix+1).equals("java")){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String lines = null;
                while ((lines = bufferedReader.readLine()) != null){
                    if (lines.startsWith("import")){
                        String importPath = lines.substring("import".length(),lines.length()-1).trim();
                        changeRecords(importPath);
                    }
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected static void changeRecords(String importPath){
        Integer sum = null;
        if ((sum = records.get(importPath)) == null){
            records.put(importPath,INIT_RECORDS_NUMBER);
        }else{
            records.put(importPath,sum + 1);
        }
    }

    /*protected static List getMostImport(){
        List<ImoprtClass> list = new ArrayList<>();

    }*/
}
