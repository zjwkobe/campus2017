package com.qunar.effectivelines.utils;

/**
 * @author ryan.hao
 * @version 1.0
 * 检查代码
 */
public class CodeChecker {

    /**
     * 查看是否为有效字符
     * @param c
     * @return
     */
    public static boolean isEffectiveChar(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '{' || c == '}';
    }

}
