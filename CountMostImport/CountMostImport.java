/**
 * @Copyright BitingWind  2016/11/23.  email: zhang_sx2013@126.com
 *
 *      A Qunar.com Examination of Fresh Graduate 2017 :
 *      get 10 imported classes that imported times is largest from all Java files in a specify root directory.
 *
 *      API :
 *             Constructor:
 *                  CountMostImport();
 *                  CountMostImport(String root);
 *            Instance Methods:
 *                                 void   setRootDir(String root)
 *                               String   getRootDir()
 *                  Map<String,Integer>   getMostImport(int k,String root);
 *                  Map<String,Integer>   getMostImport(int k);
 *                  Map<String,Integer>   getMostImportBySelectRootDirectory(int k);
 *
 */
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.JFileChooser;

public class CountMostImport {
    private String rootDir;
    private ArrayList<File> files;
    private Map<String,Integer> map;

    /**
     * default constructor without rootDir initialized
     */
    public CountMostImport(){
        files = new ArrayList<File>();
        map = new HashMap<String,Integer>();
    }

    /**
     * constructor with setting rootDir
     * @param root
     */
    public CountMostImport(String root){
        this.rootDir = root;
        files = new ArrayList<File>();
        map = new HashMap<String,Integer>();
    }

    /**
     * JavaBean,setter and getter of root directory
     */
    public void setRootDir(String root){
        this.rootDir = root;
    }
    public String getRootDir(){
        return rootDir;
    }

    /**
     * set rootDir by visual file chooser
     * @return  1 : select complete,
     *           0 : select fail (may cause by closing the window)
     */
    private int selectRootDir(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            rootDir = chooser.getSelectedFile().getAbsolutePath();
            return 1;
        }
        return 0;
    }
    /**
     * the override version of getMostImport(int k) add root directory setting
     */
    public Map<String,Integer> getMostImport(int k,String root){
        setRootDir(root);
        return  getMostImport(k);
    }
    /**
     * the public version of getMostImport(int k) ,generate the selecting operation
     */
    public Map<String,Integer> getMostImportBySelectRootDirectory(int k){
        setRootDir(null);
        return getMostImport(k);
    }
    /**
     * get the K most import class in the specify root directory
     * @param k
     *          the num of import class
     * @return the linked map contains <String,Integer> -- <import class, import times>
     * @throws IllegalArgumentException
     *          for the param k
     * @throws IllegalStateException
     *          handle the state of root directory
     */
    public Map<String,Integer> getMostImport(int k) throws IllegalArgumentException,IllegalStateException{
        // Note: generate the order that you put !!
        Map<String,Integer> result = new LinkedHashMap<String,Integer>();
        if(k <= 0)
            throw new IllegalArgumentException("请提供大于零的参数 !");
        if(rootDir == null) {
            int select = selectRootDir();
            if(select == 0)
                throw new IllegalStateException("您未选择目录 !");
        }
        getJavaFiles();
        countImport();
        ArrayList<Map.Entry<String,Integer>> importList = new ArrayList();
        for(Map.Entry<String,Integer> entry : map.entrySet())
            importList.add(entry);
        Collections.sort(importList,new Comparator<Map.Entry<String,Integer>>(){
            // descending order !!
            public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer> o2){
                return o2.getValue() - o1.getValue();
            }
        });
        int size = importList.size();
        if(size > k)size = k;
        for(int i = 0; i < size; i++)
            result.put(importList.get(i).getKey(),importList.get(i).getValue());
        return result;
    }

    /**
     * search Java files from a root directory by BFS and store them in files( instance field)
     *
     */
    private void getJavaFiles() {

        File dir = new File(rootDir);
        if(!dir.isDirectory()){
            System.out.println("请提供正确的根目录 !");
            System.exit(1);
        }
        Queue dirs = new LinkedList<File>();
        dirs.add(dir);
        while(!dirs.isEmpty())
            getJavaFiles(dirs);
    }
    /**
     * the action function of BFS
     * @param dirs
     *          the queue of directories (dynamic）
     */
    private void getJavaFiles(Queue<File> dirs){
        if(dirs.isEmpty()) return;
        File dir = dirs.poll();
        File[] fileList = dir.listFiles();
        // listFiles() :  Returns null if this abstract pathname does not denote a directory,
        // or if an I/O error occurs   (Java PlatForm SE 8 API)
        if(fileList == null){
            System.out.println(" 您在windows下选择了磁盘根目录 或者 你无权访问此目录！！");
            System.exit(1);
        }
        for(File f : fileList){
            if(f.isDirectory())
                dirs.add(f);
            else if(f.getPath().endsWith(".java"))
                files.add(f);
        }
    }

    /**
     * extract import information from java source file by removing the annotation and ending in the start of class code.
     * @param f
     *          the file object of a Java source file
     *
     * @return  the part text of Java source file that contains import
     */
    // for Java source file
    private String importInfoWithoutAnnotation(File f){
        String result = "";
        // poly Line annotation flag
        boolean polyLineAnnotation = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line = null;
            // the legacy String of a line
            String legacy = null;
            while((line = br.readLine()) != null){
                // the condition  /* **** \n
                if(polyLineAnnotation){
                    int mat = line.indexOf("*/");
                    if(mat < 0) mat = line.length();
                    if( mat < line.length()){
                        polyLineAnnotation = false;
                        // handle the condition  " */ ******"
                        legacy = line.substring(mat + 2);
                    }
                }
                // common line
                if(!polyLineAnnotation){
                    if(legacy != null){
                        line = legacy;
                        legacy = null;
                    }
                    // get thr part without annotation of each line
                    String part = null;

                    // NOTE：this project ignore the  influence from "" to judge chars !!!!!!

                    // annotation judge chars
                    int singleLineAnnotation = line.indexOf("//");
                    // rewrite the return value from -1 to N if match failed
                    if(singleLineAnnotation < 0) singleLineAnnotation = line.length();
                    int polyLine = line.indexOf("/*");
                    if(polyLine < 0) polyLine = line.length();
                    int reversePolyLine = line.indexOf("*/");
                    if(reversePolyLine < 0) reversePolyLine = line.length();

                    // handle the // and /* in one and the same line by different order
                    if(singleLineAnnotation < polyLine) part = line.substring(0,singleLineAnnotation);
                    else if(polyLine < singleLineAnnotation){
                        polyLineAnnotation = true;
                        part = line.substring(0,polyLine);
                        // handle the condition :  /*  *******  */
                        if(reversePolyLine < line.length() && reversePolyLine > polyLine ){
                            polyLineAnnotation = false;
                            // handle the conditions :  "/*  ***  //  ****  */"  and "/*  ***  */ **** // *****"
                            if(reversePolyLine < singleLineAnnotation)
                                part += line.substring(reversePolyLine + 2,singleLineAnnotation);
                        }
                    }
                    else part = line;
                    result = result + part + "\n";
                    // find the break condition
                    if(part.matches(".*\\{.*")) break;
                }
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally{
            try{
                if(br != null)
                    br.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        return result;
    }

    /**
     * update map(field) by counting the imported class in each Java file
     */
    private void countImport(){
        for(File f : files){
            String importTxt = importInfoWithoutAnnotation(f);
            Pattern p = Pattern.compile("import +.*;");
            Matcher m = p.matcher(importTxt);
            Set<String> set = new HashSet();
            while(m.find()){
                // trim() aim to remove the space in bath ends
                String importInfo = m.group().substring(6,m.group().length() - 1).trim();
                if(importInfo.endsWith("*")) continue;
                set.add(importInfo);
            }
            // remove duplicate in one and the same file using the Set<>
            for(String importInfo : set){
                if(map.get(importInfo) == null)
                    map.put(importInfo,1);
                else {
                    int num = map.get(importInfo);
                    num++;
                    map.put(importInfo,num);
                }
            }
        }
    }

    public static void main(String[] args){
        // 注意 : windows下直接选择磁盘根目录（如 "H:\\"）访问会被拒绝 ！！！

       //  CountMostImport cmi = new CountMostImport();
        //  cmi.setRootDir("H:\\com\\");
       //  Map<String,Integer> result = cmi.getMostImport(10);

        // CountMostImport cmi = new CountMostImport();
        // Map<String,Integer> result = cmi.getMostImportBySelectRootDirectory(10);

        CountMostImport cmi = new CountMostImport();
        Map<String,Integer> result = cmi.getMostImport(10,"H:\\com\\");
        System.out.println(result);
    }
}