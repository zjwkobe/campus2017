import java.io.*;
/**
 * Created by mumu462 on 2017/1/5.
 */
public class EffectiveLines
{
    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\去哪儿\\hello\\src\\Main.java"));
        String line = null;
        int linevalue=0;
        while ((line = reader.readLine() )!= null)
        {
            line=line.trim();
            if(line.equals("")||(line.charAt(0)=='/'&&line.charAt(1)=='/')||(line.charAt(0)=='{')||(line.charAt(0)=='}') )
            {
                continue;
            }
            else
                linevalue++;
        }
        System.out.println(linevalue);
    }
}
