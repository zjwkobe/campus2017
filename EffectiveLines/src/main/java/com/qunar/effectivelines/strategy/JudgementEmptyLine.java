package com.qunar.effectivelines.strategy;

/**
 * @author ryan.hao
 * @version 1.0
 * 判断空行无效的情况
 */
public class JudgementEmptyLine implements JudgementInterface {

    @Override
    public boolean judge(String line) {
        if (line == null) {
            throw new IllegalArgumentException("传入的代码行为null");
        }

        return line.trim().length() == 0;
    }
}
