import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by shj on 16-12-21.
 */
//sssssss
public class EffectiveLInes {
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if (tempString.length()==0||tempString.startsWith("//")){
                    System.out.println("line *: "  +tempString);
                    continue;
                }
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            System.out.println("********************************************");
            System.out.println("the count is "+--line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    public static void main(String[] args){
        System.out.println("Please input a filename: ");
        String fileName=null;
        Scanner input=new Scanner(System.in);
        fileName=input.nextLine();
        //readFileByLines("/home/shj/IdeaProjects/EffectiveLines/src/EffectiveLInes.java");
        readFileByLines(fileName);
    }
}
