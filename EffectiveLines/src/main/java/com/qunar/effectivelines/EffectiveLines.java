package com.qunar.effectivelines;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qunar.effectivelines.strategy.Judge;

/**
 * @author ryan.hao
 * @version 1.0
 * 程序主要流程类
 */
public class EffectiveLines {

    /**
     * 日志
     */
    private Logger logger = null;

    /**
     * 代码文件的内容
     */
    private List<String> content = null;

    /**
     * 判定业务
     */
    private Judge judge = null;


    public EffectiveLines(String url) {
        logger = LoggerFactory.getLogger(EffectiveLines.class);
        judge = new Judge();
        loadFile(url);
    }

    /**
     * 加载文件
     * @param url 文件路径
     */
    private void loadFile(String url) {
        logger.debug("开始加载文件：{}" + url);
        File file = new File(url);
        try {
            content = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("文件读取时遇到异常：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 清点有效行
     * @return
     */
    public int countEffectiveLines() {
        int count = 0;
        for (String line : content) {
            if (judge.judge(line))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(EffectiveLines.class);
        logger.info("程序开始运行");
        try {
            EffectiveLines effectiveLines = new EffectiveLines("src/main/java/com/qunar/effectivelines/EffectiveLines.java");
            int count = effectiveLines.countEffectiveLines();
            logger.info("有效行：{}" + count);
        } catch (Exception e) {
            logger.error("程序运行时出现异常：{}", e.getMessage(), e);
        }
    }

}
