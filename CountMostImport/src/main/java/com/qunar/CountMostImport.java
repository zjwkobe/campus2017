package com.qunar;

/**
 * Created by Yuan on 2017/1/10.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CountMostImport {

    static ArrayList<File> fileList = new ArrayList<File>();
    static boolean multiLine = false; // 多行注释标记

    public static void main(String[] args) {
        System.out.print("Please input the full path of target folder : ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File root = new File(path);
        if (!root.isDirectory()) {
            System.out.println("Error : You should input the folder path");
        } else {
            findFile(root);
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            Pack[] list = null;
            String lineStr = null;
            String className = null;
            try {
                for (File file : fileList) {
                    scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        lineStr = scanner.nextLine().trim();
                        if (isEffectiveLine(lineStr) && lineStr.length() > 6 && lineStr.substring(0, 6).equals("import")) {
                            className = lineStr.substring(7, lineStr.length() - 1);
                            if (map.containsKey(className)) {
                                map.put(className, map.get(className) + 1);
                            } else {
                                map.put(className, 1);
                            }
                        }
                    }
                }
                list = new Pack[map.size()];
                int count = 0;
                for (String str : map.keySet()) {
                    list[count++] = new Pack(str, map.get(str));
                }
                Arrays.sort(list, Collections.reverseOrder());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }

            System.out.println("The top 10 imported class :");
            for (int i = 0; i < 10 && i < list.length; i++) {
                System.out.printf("%-60s  %-5d time(s)\n", list[i].getClassName(), list[i].getCount());
            }
        }
    }

    private static void findFile(File root) {
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            for (File file : files) {
                findFile(file);
            }
        } else {
            if (root.getName().length() > 5 && root.getName().substring(root.getName().length() - 5).equals(".java")) {
                fileList.add(root);
            }
        }
    }

    private static boolean isEffectiveLine(String line) {
        boolean flag = true;
        if (line.length() == 0) {
            flag = false;// 空行
        }

        if (line.length() >= 2 && line.substring(0, 2).equals("/*")) {
            multiLine = true;
        }

        if (multiLine) {
            flag = false;
        } else {
            if (line.length() >= 2 && line.substring(0, 2).equals("//")) {
                flag = false;
            }
        }

        if (line.length() >= 2 && line.substring(line.length() - 2).equals("*/")) {
            multiLine = false;
        }

        return flag;
    }

}

class Pack implements Comparable<Object> {
    private String className;
    private int count;

    public Pack() {
        super();
    }

    public Pack(String className, int count) {
        super();
        this.className = className;
        this.count = count;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(Object obj) {
        if (this == obj) {
            return 0;
        } else if (obj != null && obj instanceof Pack) {
            Pack pack = (Pack) obj;
            if (count < pack.getCount()) {
                return -1;
            } else if (count == pack.getCount()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}
