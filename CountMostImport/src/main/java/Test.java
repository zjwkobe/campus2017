import java.io.*;
import java.util.*;

/**
 * Created by joe on 2016/11/14.
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("D:\\works\\campus2017\\ExchangeRate\\src\\main\\java\\com\\qunar\\homework");
//        File file = new File("D:\\Program Files\\java\\jdk1.8.0_65\\src");

        StringCount count = new StringCount();
        importFile(file, count);

        List<StringCount.Map> list = count.list;

        list.sort((o1, o2) -> o1.compareTo(o2));

        Collections.reverse(list);
        list.forEach(System.out::println);

    }

    static void importFile(File file, StringCount map) {
        if (file.isFile()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String s = null;
                String className;
                while ((s = bufferedReader.readLine()) != null && !s.startsWith("public")) {
                    s = s.trim();
                    if (s.startsWith("import")) {
                        className = s.substring(6, s.indexOf(";")).trim();
                        map.put(className);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                importFile(files[i], map);
            }
        }
    }
}
