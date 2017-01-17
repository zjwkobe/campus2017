package com.company;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    private static Map<String, Integer> importClassMap = new HashMap<>();
    public static Pattern importClassPattern = Pattern.compile("^import*", Pattern.MULTILINE);

    public static void main(String[] args) {
        System.out.println("输入目录路径：");
        String path = "";
        Scanner input = new Scanner(System.in);
        path = input.nextLine();

        try {
            getAnswer(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getAnswer(String path) throws IOException {
        countAllImport(path);
        String name = "No Import Class.";
        Integer cnt = 0;

        Set entries = importClassMap.entrySet();
        if (entries != null) {
            Iterator ite = entries.iterator();
            while (ite.hasNext()) {
                Map.Entry entry = (Map.Entry) ite.next();
                String class_name = (String) entry.getKey();
                Integer class_cnt = (Integer) entry.getValue();
                if (class_cnt > cnt) {
                    name = class_name;
                    cnt = class_cnt;
                }
            }
        }

        System.out.println("Most import class is: " + getImportName(name));
        System.out.println("This class has been imported for " + cnt + " times.");

    }

    public static String getImportName(String import_str) {
        String ret = "";
        ret = import_str.substring(6, import_str.length() - 1);
        return ret;
    }



    public static void countAllImport(String path) throws IOException {
        List<File> file_list = new ArrayList<>();
        file_list = getAllFiles(path, file_list);
        for (File file : file_list) {
            if (file.isDirectory()) {
                String next_path = path + "/" + file.getName();
                countAllImport(next_path);
            } else {
                countImport(file);
            }
        }
    }

    public static void countImport(File file) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";

            while ((line = br.readLine()) != null) {
                if (importClassPattern.matcher(line).find()) {
                    line = line.replaceAll("\\s", "");
                    if (!importClassMap.containsKey(line)) {
                        importClassMap.put(line, 1);
                    } else {
                        importClassMap.put(line, importClassMap.get(line) + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    public static List<File> getAllFiles(String path, List<File> list) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        getAllFiles(f.getAbsolutePath(), list);
                    } else {
                        list.add(f);
                    }
                }
            }
        }
        return list;
    }
}
