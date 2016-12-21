package com.yuanhaolv.service;

import com.yuanhaolv.api.ILoadFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("loadFileImpl")
public class LoadFileImpl implements ILoadFile {
    public List<File> loadJavaSourceFile(File path) throws IOException {
        List<File> files = new ArrayList<File>();
        if (path.isFile()){//若输入的路径直接定位到文件
            if (isJavaSourceFile(path)){
                files.add(path);
            }
        }
        if (path.isDirectory()){//若输入的路径是一个目录
            String parent = path.getAbsolutePath();
            String[] fileList = path.list();
            for (int i = 0; i < fileList.length; i++){
                File temp = new File(parent + "//" + fileList[i]);
                List current = loadJavaSourceFile(temp);
                files.addAll(current);
            }
        }

        return files;
    }
    /**
     *
     *   Description: 判断这个文件是不是Java源文件，方法是根据文件扩展名来判断，如果扩展名是java，则返回true
     *   @authour 元浩
     *   @date 2016/12/21
     *
     *   @param file 该文件的路径
     *   @return boolean 如果是Java源文件的话，返回true，否则返回false
     *   @throws
     *   @since
     **/
    private boolean isJavaSourceFile(File file){
        String name = file.getName();
        if (name.endsWith("java"))
            return true;
        return false;
    }
}
