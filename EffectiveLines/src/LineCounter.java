/**
 * Created by Airlan on 2016/12/21.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//用正则表达式
public class LineCounter {
    static int codeLines=0;		//代码行数
    static int whiteLines=0;	//空行数
    static int commentLines=0;	//注释行数
    static int tatolLines=0;	//总行数
    static boolean bComment=false;
    static int importLines=0;
    private static ArrayList<File>list=null;
    //FileReader fileReader=null;
    //BufferedReader bufferedReader=null;
    public LineCounter(){
        list=new ArrayList<File>();
    }
    //输出有效行数和注视行数
    public static void Count(String projectName){
        String path = LineCounter.class.getResource("/").getPath();  // 同下个path
        System.out.println(path);
        path = path.substring(0, path.length() - 30)  + "src";
        System.out.println(path);
        File file=new File(path);
        File[]files=file.listFiles();
        addList(files);
        isDirectory(files);
        readLinePerFile();
        System.out.println("代码行数="+codeLines);
        System.out.println("空行数="+whiteLines);
        System.out.println("注释行数="+commentLines);
        System.out.println("总行数="+tatolLines);


    }
    //判断是否是目录
    public static void isDirectory(File[] files){
        for(File file:files){
            if(file.isDirectory()){
                File[]file1=file.listFiles();
                addList(file1);
                isDirectory(file1);
                continue;
            }
        }
    }
    //src下的所有文件组织成List
    public static void addList(File[]files){
        for(File s:files){
            list.add(s);
        }
    }
    //读取非空行
    public static void readLinePerFile(){
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        for(File file:list){
            if(file.isDirectory()){
                continue;
            }
            int lineNumbers=codeLines;
            int blankNumbers=whiteLines;
            int comment=commentLines;
            int sum=tatolLines;
            try {
                fileReader=new FileReader(file);
                bufferedReader=new BufferedReader(fileReader);
                String i=null;
                try {
                    while((i=bufferedReader.readLine())!=null){
                        ++sum;
                        isBlankLine(i);
                        isCommentLine(i);
                        isImportLine(i);
                    }
                    int zong=sum-tatolLines;
                    int kong=whiteLines-blankNumbers;;
                    int zhu=commentLines-comment;
                    int you=zong-kong-zhu;
                    System.out.println(file.getName()+"总行数="+zong);
                    System.out.println(file.getName()+"空行数="+kong);
                    System.out.println(file.getName()+"注释行数="+zhu);
                    System.out.println(file.getName()+"有效行数="+you);
                    System.out.println(file.getName()+"import的数量="+importLines);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(fileReader!=null)
                    try {
                        fileReader.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static void isImportLine(String i) {
        // TODO Auto-generated method stub
        if(i.trim().startsWith("import java")){
            importLines++;
        }
    }
    //判断是否是空行
    public static void isBlankLine(String str){
        if(str.length()==0)	{
            whiteLines++;

        }
        //开头是一个空白符但是不是一个回车符，这样的字符有0个或者多个
        else if(str.matches("^[\\s&&[^\\n]]*$")){
            whiteLines++;
        }

    }
    //判断是否是注释行
	/*单行注释*/
    /**单行注释*/
    public static void isCommentLine(String str){
        if((str.trim().startsWith("/*") ||str.trim().startsWith("/**"))&& !str.trim().endsWith("*/")){
            commentLines++;
            bComment=true;
        }else if(bComment==true){
            commentLines++;
            if(str.trim().endsWith("*/")){
                bComment=false;
            }
        }else if((str.trim().startsWith("/*")||str.trim().startsWith("/**")) && str.trim().endsWith("*/")){
            commentLines++;
        }else if(str.trim().startsWith("//")){
            commentLines++;
        }else{
            codeLines++;
        }


    }


}

