import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/18.
 */
public class EffectiveLines {
    //总行数
    static int totalLines = 0;
    //有效行
    static int effectiveLines = 0;
    //空行
    static int blankLines = 0;
    //注释行
    static int nodeLines = 0;
    //多行注释临时记录
    static int multLines = 0;
    //标识是否处于注释行内部
    static boolean isEnd = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();
        File file = new File(path);
        countLines(file);
    }

    public static void countLines(File file) {
        String sb = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            while ((sb = br.readLine()) != null) {
                totalLines++;
                if(!countBlankLines(sb)) {
                    countNodeLines(sb);
                }
            }
            effectiveLines = totalLines - blankLines - nodeLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("total = " + totalLines);
        System.out.println("blank = " + blankLines);
        System.out.println("node = " + nodeLines);
        System.out.println("effect = " + effectiveLines);
    }

    public static boolean countBlankLines(String str) {
        if(str.matches("^\\s*")) {
            blankLines++;
            return true;
        }
        return false;
    }

    public static void countNodeLines(String str) {
        String strTrim = str.trim();
        System.out.println(strTrim);
        //记录单行注释//
        if(strTrim.startsWith("//")) {
            nodeLines++;
        }
        //匹配注释首部/*
        else if (strTrim.startsWith("/*") && isEnd == true) {
            if(!strTrim.endsWith("*/")) {
                multLines++;
                isEnd = false;
            }
        }
        //匹配注释尾部*/
        else if (strTrim.endsWith("*/")) {
            multLines++;
            isEnd = true;
            nodeLines += multLines;
            multLines = 0;
        }
        //处于注释行中
        else if (isEnd == false) {
            multLines++;
        }
    }
}
