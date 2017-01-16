import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/2.
 */
public class CountMostImport {

    private static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        getAllClasses(new File(str));
        for (String s : getMostTenImportClassName()) {
            System.out.println(s);
        }
    }

    public static void getAllClasses(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                readFile(f);
            }
        }
        else {
            System.out.println("This is not a directory!");
        }
    }

    public static void readFile(File file) {
        if(file.isFile()) {
            BufferedReader br = null;
            String str = null;
            try {
                br = new BufferedReader(new FileReader(file));
                while ((str = br.readLine()) != null) {
                    str = str.trim();
                    //过滤无import的文件
                    if (str.startsWith("public") || str.startsWith("class")) {
                        break;
                    }
                    if (str.startsWith("import")) {
                        Matcher matcher = Pattern.compile("import\\s*(.*)\\s*;").matcher(str);
                        if (matcher.find()) {
                            str = matcher.group(1);
                            //向map中添加类及修改被import的次数
                            if (map.containsKey(str)) {
                                map.put(str, map.get(str) + 1);
                            }
                            else {
                                map.put(str, 1);
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  static List<String> getMostTenImportClassName() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.toString());
            list.add(entry);
        }
        //自定义排序规则
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int i = 0;
        List<String> result = new ArrayList<String>(10);
        //获取前十个
        for (Map.Entry<String, Integer> entry : list) {
            if(++i > 10) {
                break;
            }
            result.add(entry.getKey());
        }
        return result;
    }
}
