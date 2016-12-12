package com.qunar.effectivelines.strategy;

/**
 * @author ryan.hao
 * @version 1.0
 * Java文件有效行判定接口
 */
public interface JudgementInterface {

    /**
     * 判定传入的字符串是否为有效的代码行
     * @param line 代码行
     * @return 判定结果，true表示有效，false表示无效
     */
    boolean judge(String line);

}
