package com.qunar.effectivelines.strategy;

import com.qunar.effectivelines.utils.CodeChecker;

/**
 * @author ryan.hao
 * @version 1.0
 * 判断是注释行的情况
 */
public class JudgementComment implements JudgementInterface {

    @Override
    public boolean judge(String line) {
        if (line == null) {
            return false;
        }
        int commentIndex = line.indexOf('/');
        if (commentIndex == -1) return true;
        // 查看注释行之前是否存在字母
        for (int i = 0; i < commentIndex; i++) {
            if (CodeChecker.isEffectiveChar(line.charAt(i)))
                return true;
        }
        return false;
    }
}
