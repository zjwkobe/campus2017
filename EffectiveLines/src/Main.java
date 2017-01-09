/**
 * Created by George on 2016-12-11.
 */
import java.io.*;

public class  Main{
    static int effectiveLine = 0;//有效行
    static int commentLine = 0;//注释行
    static int spaceLine = 0;//空行
    static boolean flagComment = false;//注释标志位

    public static void main(String[] args) throws IOException {
        Reader in  = new FileReader("start.java");//读取java文件
        BufferedReader bufferedReader = new BufferedReader(in);//读取到缓冲区
        String str = null;
        while((str = bufferedReader.readLine()) != null)//每次读取一行进行操作
            count(str);
        System.out.println("该文件有效代码行：" + effectiveLine);
    }

    private static void count(String line) {
        String regExBegin = "\\s*/\\*.*";//正则匹配注释多行开头
        String regExEnd = ".*\\*/\\s*";//正则匹配多行注释结尾
        String regEx = "//.*";//正则匹配单行注释
        String regExSpace = "\\s*";//正则匹配空行
        if(line.matches(regExBegin) && line.matches(regExEnd)){
            ++commentLine;
            return ;
        }
        if(line.matches(regExBegin)){
            ++commentLine;
            flagComment = true;
        } else if(line.matches(regExEnd)){
            ++commentLine;
            flagComment = false;
        } else if(line.matches(regExSpace))
            ++spaceLine;
        else if(line.matches(regEx))
            ++commentLine;
        else if(flagComment)
            ++commentLine;
        else
            ++effectiveLine;
    }
}