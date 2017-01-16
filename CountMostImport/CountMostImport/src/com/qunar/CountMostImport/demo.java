package com.qunar.CountMostImport;

import java.io.*;
import java.util.*;

/**
 * Created by jk on 2016/12/21.
 */
public class demo {
    public static Map<String, Integer> CountImportMap = new TreeMap<String, Integer>();

    public static void statistic(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File file1 : files) {
                statistic(file1);
            }
        }
        if (file.isFile()) {
            if (file.getName().matches(".*\\.java")) {
                parse(file);
            }
        }
    }

    public static void parse(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("import")) {
                //System.out.println(line);
                /*Pattern pattern = Pattern.compile("(?<=import)");
                Matcher matcher = pattern.matcher(line);
                System.out.println(matcher.group());*/
                String className = line.replaceAll("import|;", "");
                CountMap(className);
            }
        }
    }

    private static void CountMap(String className) {
        if (CountImportMap.get(className) == null) {
            CountImportMap.put(className, 1);
        } else {
            Integer Num = CountImportMap.get(className);
            CountImportMap.put(className, Num + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        String fileName;
        Scanner sc=new Scanner(System.in);
        System.out.println("输入目录");
        fileName = sc.nextLine();
        File file = new File(fileName);
        statistic(file);
        System.out.println(getMaxKey(CountImportMap));
        System.out.println(getTop10());
    }

    public static Object getMaxKey(Map<String, Integer> map) {
        if (map == null) return null;
        Integer MostVal = 0;
        String Mostkey = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() > MostVal) {
                MostVal = entry.getValue();
                Mostkey = entry.getKey();
            }
        }
        return Mostkey;
    }

    public static List<Object> getTop10() {
        Map<String, Integer> tempMap = CountImportMap;
        List<Object> list = new LinkedList<Object>();
        int n = tempMap.size();
        if (n > 10) {
            for (int i = 0; i < 10; i++) {
                list.add(getMaxKey(tempMap));
                tempMap.remove(getMaxKey(tempMap));
            }
        }else {
            for (int i = 0; i < n; i++) {
                list.add(getMaxKey(tempMap));
                tempMap.remove(getMaxKey(tempMap));
            }
        }
        return list;
    }

}
