package com.yuanhao.controller;

import com.yuanhao.constant.Constant;
import com.yuanhao.model.Kind;
import com.yuanhao.service.IAnalyze;
import com.yuanhao.util.EncodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.Map.Entry;

@Controller
public class CharacterController {
	@Autowired
	IAnalyze analyzeImpl;

	@RequestMapping("/result")
	public ModelAndView analyze(@RequestParam("content") String content){
		List<Map<Object, Integer>> result = analyzeImpl.analyze(EncodingUtil.encodeStr(content));

		Map<Object, Integer> kindMap = result.get(0);
		TreeMap<Object, Integer> charMap = (TreeMap<Object, Integer>) result.get(1);
		Integer number = kindMap.get(Kind.NUMBER);
		Integer english = kindMap.get(Kind.ENGLISH_CHARACTER);
		Integer chinese = kindMap.get(Kind.CHINESE_CHARACTER);
		Integer punctuation = kindMap.get(Kind.PUNCTUATION);

		Map<String, String> data = new HashMap<String, String>();
		//将原文封装进去
		data.put(Constant.CONTENT, EncodingUtil.encodeStr(content));
		//将数字封装好
		if (number == null){
			data.put(Constant.NUMBER_COUNT, "0");
		}else{
			data.put(Constant.NUMBER_COUNT, String.valueOf(number));
		}
		//封装字母
		if (english == null){
			data.put(Constant.ENGLISH_COUNT, "0");
		}else{
			data.put(Constant.ENGLISH_COUNT, String.valueOf(english));
		}
		//封装汉字
		if (chinese == null){
			data.put(Constant.CHINESE_COUNT, "0");
		}else{
			data.put(Constant.CHINESE_COUNT, String.valueOf(chinese));
		}
		//封装标点
		if (punctuation == null){
			data.put(Constant.PUNCTUATON_COUNT, "0");
		}else{
			data.put(Constant.PUNCTUATON_COUNT, String.valueOf(punctuation));
		}

		List<Map.Entry<Object, Integer>> list = new ArrayList<Map.Entry<Object,Integer>>(charMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Object, Integer>>() {

			public int compare(Entry<Object, Integer> o1, Entry<Object, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		int size = list.size();
		int count = (size >= 3) ? 3 : size;
		for (int i = 0; i < count; i++){
			Map.Entry<Object, Integer> entry = list.get(i);
			data.put(Constant.CHARACTER[i], String.valueOf((Character)entry.getKey()));
			data.put(Constant.TIMES[i], String.valueOf(entry.getValue()));
		}

		return new ModelAndView("result", data);
	}

}

