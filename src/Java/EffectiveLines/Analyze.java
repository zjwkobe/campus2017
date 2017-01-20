package Java.EffectiveLines;


import java.io.*;

/**
 * Created by hk__lrzy on 2017/1/20.
 */
public class Analyze {
    private static int effectivelines;

    public static int AnalyzeFile(String filePath){
        readFile(filePath);
        return effectivelines;
    }


    public static void readFile(String filePath){
        String targetFileSuffix = ".java";
        if (filePath.substring(filePath.lastIndexOf('.')).equals(targetFileSuffix)){
            File file = new File(filePath);
            try {
                if (file.exists()){
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String lines = null;
                    while((lines = bufferedReader.readLine())!= null){
                        if (judgeEffectiveLines(lines)){
                            effectivelines++;
                        }
                    }

                }else{
                    System.out.println("file not exists");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("This is wrong file type");
        }
    }

    public static boolean judgeEffectiveLines(String line){
        line = line.trim();
        if (line.isEmpty()){
            return false;
        }else if(line.matches("\\/\\/[^\\n]*")){
            return false;
        }else if(line.matches("\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/")){
            return false;
        }
        return true;
    }

}
