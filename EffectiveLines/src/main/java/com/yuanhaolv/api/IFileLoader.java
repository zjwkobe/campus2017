package com.yuanhaolv.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 元浩 on 2016/12/21.
 */
public interface IFileLoader {
    /**
     *
     *   Description: 加载文件中内容
     *   @authour 元浩
     *   @date 2016/12/21
     *
     *   @param file the file to be loaded
     *   @return StringBuffer the content of the file
     *   @throws FileNotFoundException IOException
     *   @since
     **/
    StringBuffer loadContent(File file) throws FileNotFoundException, IOException;
}
