/**
 * Created by Airlan on 2016/12/21.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


public class MostImport {

    static ArrayList<File>list=null;
    static HashMap<String,String>map=null;
    static int importNumbers=0;
    static ArrayList<String>listString=null;
    static TreeSet<String>set=null;

    public static void getCount(String name){

        String path=MostImport.class.getResource("/").getPath();
        map=new HashMap<String,String>();
        list=new ArrayList<File>();
        listString=new ArrayList<String>();
        path=path.substring(0, path.length()-47)+name+"/src";
        System.out.println(path);
        File file=new File(path);
        File[]files=file.listFiles();
        addList(files);
        isDirectory(files);
        //System.out.println(list);
        countImport();
    }

    public  static void countClassImport(String s){
        String classStr=s.substring(s.lastIndexOf('.')+1);
        if(map.containsKey(s)){
            String str=map.get(s);
            int num=Integer.parseInt(str);
            num+=1;
            String str1=Integer.toString(num);
            map.put(s,str1);

        }
        else{
            map.put(s,"1");
            listString.add(s);
        }
    }

    private static void countImport() {
        // TODO Auto-generated method stub
        FileReader fileReader=null;
        BufferedReader buffereReader=null;
        int num;
        for(File file:list){
            if(file.isDirectory()){
                continue;
            }

            try {
                fileReader=new FileReader(file);
                buffereReader=new BufferedReader(fileReader);
                String i=null;
                try {
                    while((i=buffereReader.readLine())!=null){

                        if(i.trim().startsWith("import java")){
                            countClassImport(i);
                        }
                    }


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if(fileReader!=null){
            try {
                fileReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(buffereReader!=null){
            try {
                buffereReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void isDirectory(File[]files){
        for(File file:files){
            if(file.isDirectory()){
                File[]files1=file.listFiles();
                addList(files1);
                isDirectory(files1);
            }
        }
    }

    public static void addList(File[]files){
        for(File f:files){
            list.add(f);
        }
    }

    public static void sort(){

    }
}

