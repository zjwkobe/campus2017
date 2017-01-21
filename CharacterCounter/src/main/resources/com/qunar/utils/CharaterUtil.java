package com.qunar.utils;

/**
 * 字符工具类
 * Created by NULL on 2017/1/21.
 */
public class CharaterUtil {

    public static final int CHARACTER_TYPE_UNKNOW = 0x00;         // 未知
    public static final int CHARACTER_TYPE_LETTER = 0x01;         // 字母
    public static final int CHARACTER_TYPE_NUMBER = 0x02;         // 数字
    public static final int CHARACTER_TYPE_PUNCTUATION = 0x03;    // 符号
    public static final int CHARACTER_TYPE_CHINESE = 0x04;        // 汉字

    /**
     * 判断一个字符是ASCII字符还是其它字符（如汉，日，韩文字符）
     * @param c
     * @return
     */
    public static boolean isASCIILetter(char c) {
        int k = 0x80;
        return (c / k) == 0 ? true : false;
    }

    /**
     * 判断一个字符是否是英文字母
     * @param c
     * @return
     */
    public static boolean isEnglishLetter(char c) {
        if ((c>=97 && c<=122) || c>=65 && c<=90) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个字符是否是数字
     * @param c
     * @return
     */
    public static boolean isNumber(char c) {
        if (c>=48 && c<=57) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个字符是否是英文标点符号
     * @param c
     * @return
     */
    public static boolean isEnglishPunctuation(char c) {
        if ( (c>=32 && c<=47) || (c>=58 && c<=64) || (c>=91 && c<=96) || (c>=123 && c<=126)) {
            return true;
        }
        return false;
    }

    /**
     * 根据Unicode编码判断是否是中文汉字
     * @param c
     * @return
     */
    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (    ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || /*CJK 统一表意符号 */
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || /*CJK 兼容象形文字 */
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || /*CJK 统一表意符号扩展 A */
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B /*CJK 统一表意符号扩展 B*/
                ) {
            return true;
        }
        return false;
    }

    /**
     * 根据Unicode编码判断是否是中文标点符号
     * @param c
     * @return
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION/*CJK 符号和标点 */ || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS/*半角及全角形式*/) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符类型
     * @param ch
     * @return 返回类型编号
     */
    public static int typeOfChar(char ch){

        // 是英文字符
        if(isASCIILetter(ch)){
            if(isEnglishLetter(ch))
                return CHARACTER_TYPE_LETTER;
            else if(isEnglishPunctuation(ch))
                return CHARACTER_TYPE_PUNCTUATION;
            else if(isNumber(ch))
                return CHARACTER_TYPE_NUMBER;
            else
                return CHARACTER_TYPE_UNKNOW;
        }

        // 不是英文字符
        else{
            if(isChineseCharacter(ch))
                return CHARACTER_TYPE_CHINESE;
            else if(isChinesePunctuation(ch))
                return CHARACTER_TYPE_PUNCTUATION;
            else
                return CHARACTER_TYPE_UNKNOW;
        }

    }



}
