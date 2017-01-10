import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wen on 2016/12/22.
 */
public class EffectiveLines
{
    static private int blankLine = 0;
    static private int commetnLine = 0;
    static private int codeLine = 0;
    static private boolean comment = false;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("src\\EffectiveLines.java"));
            String line;
            while ((line = br.readLine()) != null)
            {
                parse(line);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                    br = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("blankLine:" + blankLine);
        System.out.println("commetnLine:" + commetnLine);
        System.out.println("codeLine:" + codeLine);
    }

    private static void parse(String line)
    {
        line = line.trim();
        if (line.matches("^[\\s]*$"))
        {
            blankLine++;
        }
        else if (line.startsWith("/*"))
        {
            commetnLine++;
            if (!line.endsWith("*/"))
            {
                comment = true;
            }
        }
        else if (comment)
        {
            commetnLine++;
            if(line.endsWith("*/"))
            {
                comment = false;
            }
        }
        else if (line.startsWith("//"))
        {
            commetnLine++;
        } else
        {
            codeLine++;
        }
    }
}
