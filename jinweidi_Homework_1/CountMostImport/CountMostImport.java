import java.io.File;
import java.util.*;

/**
 * Created by Readdy on 2016/12/8.
 * @version 0.2.1 2017/01/01
 * @author weidi.jin
 */
public class CountMostImport {
    public static void main(String[] args) {

        System.out.print("Java source files directory:");

        Scanner stdin = new Scanner(System.in);
        String aDir = stdin.next();

        Counter aCounter = new Counter(aDir);
        aCounter.countMostImport();
    }
}

class Counter {
    private String directory = "";
    private Map<String, Integer> classSet = new TreeMap<String, Integer>();
    private Integer javaSrcFileCount = 0;

    public Counter(String aDirectory) {
        this.directory = aDirectory;
    }

    public int countMostImport() {
        File file = new File(this.directory);

        // 1. testing if directory is a actual directory
        if ( !file.isDirectory() ) {
            System.out.println("[ Error ]: \""+this.directory+"\" is not a directory!");
            return -1;
        }

        // 2. get file list
        File[] fileArr = file.listFiles();
        if (0 == fileArr.length) {
            System.out.println("[ Warning ] \"" + this.directory + "\" has no file!");
            return -1;
        }

        // 3. count import

        for (File f: fileArr) {
            if ( f.getName().endsWith(".java") ) {
                //System.out.println("File Name: <"+f.getName()+">");
                this.counting(f.getPath());
                ++javaSrcFileCount;
            }
        }
        if (0 == javaSrcFileCount) {
            System.out.println("[ Warning ] \""+this.directory+"\" has no effective java source file!");
            return -1;
        }

        // 4. sorting
        List<Map.Entry<String,Integer>> list = new ArrayList<>(classSet.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        // 5. output head 10
        System.out.println("\nTotal "+javaSrcFileCount+" java source files.\n"
                +"Top 10 import class are:\n"
                +"      ClassName Times");
        Integer i = 0;
        while (i < 10 && i < list.size()) {
            System.out.printf("%15s %d\n", list.get(i).getKey(), list.get(i).getValue());
            ++i;
        }

        return 0;
    }

    public int counting(String filePath) {
        File file = new File(filePath);
        Scanner javaSrcFile;
        try {
            javaSrcFile = new Scanner(file);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("[ Error ] "+filePath+" not found!");
            return -1;
        }

        /*String regexp = "^import +(\\w+\\.)+\\w+;";
        Pattern pt = Pattern.compile(regexp);

        while ( javaSrcFile.hasNextLine() ) {
            String aLine = javaSrcFile.nextLine().trim();
            Matcher mc = pt.matcher(aLine);

            if ( mc.find() ) {
                System.out.println(aLine);
            }

            if ( aLine.matches("^import +(\\w+\\.)+\\w+;") ) {
                System.out.println(aLine);
            }
        }*/

        while ( javaSrcFile.hasNextLine() ) {
            String aLine = javaSrcFile.nextLine().trim();

            if ( aLine.matches("^import +(\\w+\\.)+\\w+ *;") ) {

                String className = aLine.substring(aLine.lastIndexOf('.') + 1, aLine.length() - 1);

                if ( classSet.containsKey(className) ) {
                    classSet.replace(className, classSet.get(className)+1);
                }
                else {
                    classSet.put(className, 1);
                }
            }
        }

        return 0;
    }
}
