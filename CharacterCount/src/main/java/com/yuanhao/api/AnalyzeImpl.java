package com.yuanhao.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.yuanhao.model.Kind;
import com.yuanhao.service.IAnalyze;

@Service("analyzeImpl")
public class AnalyzeImpl implements IAnalyze {

	public List<Map<Object, Integer>> analyze(String content) {
		// TODO Auto-generated method stub
		List<Map<Object, Integer>> result = new ArrayList<Map<Object, Integer>>();
		Map<Object, Integer> kindMap = new HashMap<Object, Integer>();
		TreeMap<Object, Integer> charMap = new TreeMap<Object, Integer>();
		result.add(kindMap);
		result.add(charMap);

		int length = content.length();
		for (int i = 0; i < length; i++) {
			int category = category(content.charAt(i));
			// if category = -1, then the character is out of choice.
			if (category >= 0) {
				Integer count = charMap.get(content.charAt(i));
				if (count == null) {
					charMap.put(content.charAt(i), 1);
				} else {
					charMap.put(content.charAt(i), count + 1);
				}
			}

			switch (category) {
				case 0:
					Integer numberCount = kindMap.get(Kind.NUMBER);
					if (numberCount == null) {
						kindMap.put(Kind.NUMBER, 1);
					} else {
						kindMap.put(Kind.NUMBER, numberCount + 1);
					}
					break;
				case 1:
					Integer englishCount = kindMap.get(Kind.ENGLISH_CHARACTER);
					if (englishCount == null) {
						kindMap.put(Kind.ENGLISH_CHARACTER, 1);
					} else {
						kindMap.put(Kind.ENGLISH_CHARACTER, englishCount + 1);
					}
					break;
				case 2:
					Integer chineseCount = kindMap.get(Kind.CHINESE_CHARACTER);
					if (chineseCount == null) {
						kindMap.put(Kind.CHINESE_CHARACTER, 1);
					} else {
						kindMap.put(Kind.CHINESE_CHARACTER, chineseCount + 1);
					}
					break;
				case 3:
					Integer punctuationCount = kindMap.get(Kind.PUNCTUATION);
					if (punctuationCount == null) {
						kindMap.put(Kind.PUNCTUATION, 1);
					} else {
						kindMap.put(Kind.PUNCTUATION, punctuationCount + 1);
					}
					break;
				default:
					break;
			}

		}


		return result;
	}

	/**
	 * @param c
	 * @return 0 if number, 1 if English character, 2 if Chinese character, 3 if
	 *         punctuation, -1 otherwise
	 */
	private int category(char c) {
		if (isNumber(c))
			return 0;
		if (isEnglishCharacter(c))
			return 1;
		if (isChineseCharacter(c))
			return 2;
		if (isPunctuation(c))
			return 3;
		return -1;
	}

	private boolean isNumber(char c) {
		String regex = "[0-9]";// use regular
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(c + "");
		if (matcher.find())
			return true;
		return false;
	}

	private boolean isEnglishCharacter(char c) {
		String regex = "[a-zA-Z]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(c + "");
		if (matcher.find())
			return true;
		return false;
	}

	private boolean isChineseCharacter(char c) {
		return isChineseByBlock(c);
	}

	private boolean isPunctuation(char c) {
		return isChinesePunctuation(c);
	}

	// 使用UnicodeBlock方法判断
	private boolean isChineseByBlock(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
			return true;
		} else {
			return false;
		}
	}

	// 根据UnicodeBlock方法判断中文标点符号
	private boolean isChinesePunctuation(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
				|| ub == Character.UnicodeBlock.VERTICAL_FORMS) {
			return true;
		} else {
			return false;
		}
	}

}
