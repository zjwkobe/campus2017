package edu.zhangzl.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.zhangzl.entity.MapEntity;
import edu.zhangzl.tools.Handle;
import edu.zhangzl.tools.HandleFrequency;


@Controller
@RequestMapping("handleAction")
public class HandleAction {

	private MultipartFile file;
	
	private String value;
	
	@RequestMapping("/handleString.do")
	public @ResponseBody List<Integer> handleString(String value){
		this.value = value.trim(); 
		List<Integer> list = Handle.handleString(new StringBuilder(value));
		return list;
	}
	
	@RequestMapping("/handleFile.do")
	public @ResponseBody List<Integer> handleFile(MultipartFile file){
		this.file = file;
		try {
			InputStream in = file.getInputStream();
			List<Integer> list = Handle.handle_inputstream(in);
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/handlefre_String.do")
	public @ResponseBody List<MapEntity> handleFre(){
		HandleFrequency hf = new HandleFrequency();
		hf.handleFrequency(value);
		List<MapEntity> list = hf.getList();
		if(list.size()>=3){
			list = list.subList(list.size()-3, list.size());
		}
		return list;
	}
	
	@RequestMapping("/handlefre_File.do")
	public @ResponseBody List<MapEntity> handleFre_file(){
		HandleFrequency hf = new HandleFrequency();
		try {
			hf.handleFrequency(this.file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MapEntity> list = hf.getList();
		if(list.size()>=3){
			list = list.subList(list.size()-3, list.size());
		}
		return list;
	}
	
}
