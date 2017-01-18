package com.firstwork.qunar;

/**
 * Created by admin on 2017/1/17.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class EffectiveLines {


    static long normalLines = 0; // 代码行
    static long commentLines = 0; // 注释行
    static long whiteLines = 0; // 空行


    public static void main(String[] args) {
        EffectiveLines sjc = new  EffectiveLines();
        //这个路径可以通过Java1进行输入 正则表达式对路径进行合理性判断
        File f = new File("E:\\BaiduYunDownload\\Simpy\\");   //创建一个File实例
        System.out.println(f.getName());
        sjc.treeFile(f);


        System.out.println("空行：" + whiteLines);
        System.out.println("注释行：" + commentLines);
        System.out.println("代码行：" + normalLines);
    }
/*
    designd for judge this path is directory or is file
    if path is directory use recursion to calculate
* */

    private void treeFile(File f) {


        File[] childs = f.listFiles();  //获取path下所有文件 但是如果存入的路径是文件childs = null need to judge
        if(childs==null)
            return ;

        for (int i = 0; i < childs.length; i++) {
            if (!childs[i].isDirectory()) {
                // if (childs[i].getName().matches(".*.java$")) {
                    /*if(fileName.endWith(".jpg"))||fileName.endWith(".jpeg")..
                        考虑可能嵌套太多if循环：
                        public static boolean isValid(String contentType,String...allowTypes){
                            if(null==contentType||"".equals(contentType))
                                return false;
                              for(String type:allowTypes)
                                if(contentType.indexOf(type)>-1)
                                    return true;
                        }
                        String[] allowsType = new String[]{".txt",".png"};
                        Boolean Valid = isValid("file path",allowsType);

                    * */
                //如果获取的判断是文件 就对文件后缀进行判断 如果是 Java文件就进行处理
                if(true == childs[i].getName().endsWith(".Java")){
                    System.out.println(childs[i].getName());
                    sumCode(childs[i]);
                }
            } else {
                treeFile(childs[i]);
                // sum += count;
            }
        }
    }


    /**
     * 计算一个.java文件中的代码行，空行，注释行 . * 57. * @param file 58. * 要计算的.java文件 59.
     */
    private void sumCode(File file) {
        BufferedReader br = null;
        boolean comment = false;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            try {
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.matches("^[//s&&[^//n]]*$")) {
                        whiteLines++;
                    } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                        commentLines++;
                        comment = true;
                    } else if (true == comment) {
                        commentLines++;
                        if (line.endsWith("*/")) {
                            comment = false;
                        }
                    } else if (line.startsWith("//")) {
                        commentLines++;
                    } else {
                        normalLines++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {     //在这里不论如何都要进行br的关闭操作 BuffferdReader如果没有创建成功br为null所以必须进行判断
            if (br != null) {
                try {
                    br.close();   //假如br在Buffer
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}