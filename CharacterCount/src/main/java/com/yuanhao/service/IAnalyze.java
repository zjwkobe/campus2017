package com.yuanhao.service;

import java.util.List;
import java.util.Map;

public interface IAnalyze {
	/**
	 * @param content
	 * @return return two map, the first one stores the number of each kind character, the second one stores
	 * 		   the number of every character
	 */
	List<Map<Object, Integer>> analyze(String content);
}
