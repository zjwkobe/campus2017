import java.io.BufferedReader;
import java.io.FileReader;
 
public class MyFileReader {
     
    public static void main(String[] args)throws Exception{
                //文件绝对路径改成你自己的文件路径
        FileReader fr=new FileReader("D:\\workspace\\MyLearn\\count.txt");
        //可以换成工程目录下的其他文本文件
        BufferedReader br=new BufferedReader(fr);
        while(br.readLine()!=null){
            String s=br.readLine();
            System.out.println(s);
        }
        br.close();
    }
 
}