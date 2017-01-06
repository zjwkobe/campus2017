import java.io.*;
import java.util.Scanner;

public class EffectiveLines {
    public static void main(String[] args){
        System.out.println("please input file path :");
        Scanner scanner=new Scanner(System.in);
        String path=scanner.nextLine();
        countLines(path);
    }
    /*判断文件是否为java类型，若是则计算，否则输出错误信息*/
    static void countLines(String path){
        if(!path.trim().endsWith(".java")){         //若打开文件不是java文件
            System.out.println("打开文件类型错误:非java文件");
        }
        else{
            File file =new File(path);
            System.out.println(linesNum(file));
        }

    }
    /*计算有效行*/
    static  int linesNum(File file){
        int count=0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line =bufferedReader.readLine();
            while(line!=null) {
                line=line.trim();
                if (line.equals("") || line.startsWith("//") || line.startsWith("/*")) {
                } else {
                    count++;
                }
                line = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("文件未找到");
        }catch (IOException e){
            System.out.println(e);
        }
        return  count;
    }
}
