package com.yuanhaolv.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
public interface IAnalyzeImport {
    List<String> countImport(File sourceFile) throws IOException;
}
