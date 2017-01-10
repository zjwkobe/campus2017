import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenzh on 2016/12/22.
 */
public class CountMostImport
{
    static ArrayList<File> fileList = new ArrayList<File>();
    static Map<String, Integer> map = new TreeMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        String path = "D:\\IDEA\\Project";
        getFiles(path);
        getClasses();
        sortByValue();
    }

    public static void getFiles(String path)
    {
        File root = new File(path);
        if(root.exists())
        {
            File[] files = root.listFiles();
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    getFiles(file.getAbsolutePath());
                }
                else if (file.getName().endsWith(".java"))
                {
                    fileList.add(file);
                }
            }
        }
    }

    private static void getClasses() throws IOException
    {
        String pattern = "^import ([[a-zA-Z]{1,}\\.]+);$";

        for(int i=0;i<fileList.size();i++)
        {
            File file = fileList.get(i);
            BufferedReader br  =new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                String str = line;
                line.trim();
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(line);
                if(m.find())
                {
                    str = m.group(1);
                    Integer value = 1;
                    if (map.containsKey(str))
                    {
                        value = map.get(str);
                        value++;
                    }
                    map.put(str,value);
                }
            }
        }
    }

    private static void sortByValue()
    {
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>()
                {
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
                    {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }
        );

        int i = 0;
        for(Map.Entry<String,Integer> mapper : list)
        {
            if(i>=10)
                break;
            System.out.println(mapper.getValue()  + ":" + mapper.getKey());
            i++;
        }
    }
}
