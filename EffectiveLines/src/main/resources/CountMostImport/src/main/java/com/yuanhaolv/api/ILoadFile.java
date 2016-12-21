package com.yuanhaolv.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 * Description: 根据指定的工程目录，将下面的所有Java源文件加载出来
 */
public interface ILoadFile {
    List<File> loadJavaSourceFile(File path) throws IOException;
}
