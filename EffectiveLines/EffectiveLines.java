/**
 * @Copyyright BitingWind  2016/11/18.  email: zhang_sx2013@126.com
 *       A Qunar.com Examination of Fresh Graduate 2017 :
 *       for a java source file ,get the number of effective lines (ignore polyline annotation)
 *       API :
 *          Static Method :
 *              int  effectiveLines(String  filename)
 *              int  getEffectiveLinesBySelectFile()
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class EffectiveLines {
    public static int effectiveLines(String  filename){
        int result = 0;
        File f = new File(filename);
        try{
            // may throw the FileNotFoundException
            Scanner scan = new Scanner(f);
            // read each line that can be space line, annotation, common code
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                int len = line.length();
                int notSpace = 0;
                // get the  index of first char that is not space
                while(notSpace < len && line.charAt(notSpace) == ' ') notSpace++;
                // ignore space line
                if(notSpace == len) continue;
                // ignore the annotation line
                if( (notSpace < len - 1 ) && line.charAt(notSpace) == (int)'/' && line.charAt(notSpace + 1) == (int)'/')
                    continue;
                result++;
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return result;
    }
    public static int getEffectiveLinesBySelectFile() throws IllegalStateException{
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA File","java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        String path = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {
             path = chooser.getSelectedFile().getAbsolutePath();
        }
        if(path == null)
            throw new IllegalStateException("您未选择文件 !");
        return effectiveLines(path);
    }
    public static void main(String[] args){
     //   System.out.println(getEffectiveLinesBySelectFile());
        System.out.println(effectiveLines("H:\\com\\bitingwind\\CountMostImport\\src\\CountMostImport.java"));
    }
}
