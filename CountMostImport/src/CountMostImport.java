import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Administrator on 2016/12/26.
 */
public class CountMostImport {
    static ArrayList<File> arrayList;
    static Map<String,Integer> map;
    static TreeMap<Integer,ArrayList<String>> treeMap;

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入java源文件目录：");
        String path=scanner.nextLine();
        File file=new File(path);
        File[] files=file.listFiles();
        arrayList=new ArrayList<>();
        map=new HashMap<>();
        treeMap=new TreeMap<>(Collections.reverseOrder());
        addAllFiles(files);                  //添加所有待统计文件
        countImport(arrayList);              //统计所有文件中导入的类
        reverseCount();                      //反转统计结果，形成以出现次数为键，导入的类为值的map
        diaplayMost();                       //显示排名前十的类
    }


    //添加目录下所有需要统计的java文件
    static void  addAllFiles(File[] files){
        if (files.length>0) {
            for (File f : files) {
                if (f.isFile() && f.getName().endsWith(".java")) {
                    arrayList.add(f);
                }
                if (f.isDirectory()) {
                    addAllFiles(f.listFiles());
                }
            }
        }
    }

    //统计所有java文件中导入的类，其中对导入的包需要进行排除
    static void countImport(ArrayList<File>  arrayList){
        String s;
        boolean b;
        for (File f : arrayList){
            b=true;
            try  {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                s=bufferedReader.readLine();
                while (s!=null&&b){
                    if (s.trim().startsWith("import")){
                        if (!s.trim().endsWith("*;")){
                            s=s.substring(7).trim();
                            s=s.substring(0,s.length()-1);
                            if (map.containsKey(s)){
                                map.replace(s,map.get(s),map.get(s)+1);
                            }
                            else {
                                map.put(s,1);
                            }
                        }

                    }
                    else {
                        if (!s.equals(""))
                            b=false;
                    }
                    s=bufferedReader.readLine();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    //翻转统计结果
    private static void reverseCount() {

        ArrayList<String>   arrayList1;
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if (treeMap.containsKey(entry.getValue())){
                arrayList1=new ArrayList<String>(treeMap.get(entry.getValue()));
                arrayList1.add(entry.getKey());
                treeMap.replace(entry.getValue(),arrayList1);
            }
            else {
                arrayList1=new ArrayList<String>(Arrays.asList(entry.getKey()));
                treeMap.put(entry.getValue(),arrayList1);
            }

        }

    }

    //显示结果，若出现相同次数的类有一个或以上输出，则对所有出现该次数的类输出
    static void diaplayMost(){
        int i=10;
        for (Map.Entry<Integer,ArrayList<String>> entry : treeMap.entrySet()){
            if (i>0){
                    i=i-entry.getValue().size();
                    for (String s : entry.getValue()){
                        System.out.println(s);
                    }
            }
        }

    }


}
