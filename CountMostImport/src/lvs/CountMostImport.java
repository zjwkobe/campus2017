package lvs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by lvs on 2017-1-4.
 */
public class CountMostImport {

    private static Map<String,Integer> importMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File file = new File(path);
        if(file.exists()){
            countImport(file);
            getMostImport(importMap);
        }else{
            System.err.println("The file or directory is not exist");
        }
    }

    public static void countImport(File file){
        if(file.isFile() && file.getName().endsWith(".java")){
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                while (null != (line = br.readLine())) {
                    line = line.trim();
                    if (line.startsWith("import")) {
                        String importClass = line.substring(7,line.length()-1);
                        if(importMap.containsKey(importClass)){
                            importMap.put(importClass,importMap.get(importClass)+1);
                        }else{
                            importMap.put(importClass,1);
                        }

                    }
                    if(line.startsWith("public")){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(file.isDirectory()){
            for(File childFile : file.listFiles()){
                if(childFile.exists()) {
                    countImport(childFile);
                }
            }
        }
    }

    public static void getMostImport(Map importMap){
        ArrayList<Map.Entry<String,Integer>> importList = new ArrayList<>(importMap.entrySet());
        Collections.sort(importList, (o1, o2) -> (o2.getValue() - o1.getValue()));
        if (!importList.isEmpty()) {
            int count = 0;
            System.out.println("The most classes imported and their number are as follows:");
            for (Map.Entry<String, Integer> stringIntegerEntry : importList) {
                System.out.println(stringIntegerEntry.getKey() + "  :   " + stringIntegerEntry.getValue());
                count++;
                if(count == 10){
                    break;
                }
            }
        }else{
            System.out.println("There is no class imported.");
        }
    }
}
