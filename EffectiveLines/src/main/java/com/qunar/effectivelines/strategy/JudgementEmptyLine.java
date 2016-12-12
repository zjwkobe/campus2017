package com.qunar.effectivelines.strategy;

import com.qunar.effectivelines.utils.CodeChecker;

/**
 * @author ryan.hao
 * @version 1.0
 * 判断空行无效的情况
 */
public class JudgementEmptyLine implements JudgementInterface {

    @Override
    public boolean judge(String line) {
        if (line == null) {
            return false;
        }
        // 检查这一行有没有字母
        for (int i = 0; i < line.length(); i++) {
            if (CodeChecker.isEffectiveChar(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
