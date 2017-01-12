package com.read;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 在目录中寻找.java文件
 */
public class ReadFile {
    List<String> filePaths = new ArrayList<String>();

    public List<String> getPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> lists = new LinkedList<File>();
            File[] files = file.listFiles();

            for (File folder : files) {

                if (folder.isDirectory()) {
                    lists.add(folder);

                } else {
                    String filename = folder.getName();
                    String filepath = folder.getAbsolutePath().toString();
                    judgeFile(filepath);

                }
            }
            File listfile = null;
            while (lists.size() != 0) {
                listfile = lists.removeFirst();
                file = null;
                files = listfile.listFiles();
                for (File folder : files) {
                    if (folder.isDirectory()) {
                        lists.add(folder);

                    } else {
                        String filename = folder.getName();
                        String filepath = folder.getAbsolutePath();
                        judgeFile(filepath);

                    }
                }

                if (lists.isEmpty()) {
                    break;
                }
            }
        } else if (!file.exists()) {
            System.out.println("路径不存在");
        }

        return filePaths;
    }

    private void judgeFile(String str) {
        if (str.endsWith(".java")) {
            filePaths.add(str);
        }
    }
}

