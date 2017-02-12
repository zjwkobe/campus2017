package CharacterCounter.controller;


import CharacterCounter.service.HandleTextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Controller
public class HandleText

{
	HandleTextService service;

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String fileUpload(Model model, MultipartFile fileUpload) {
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(fileUpload.getInputStream()));
			StringBuffer content=new StringBuffer();
			String line="";
			while ((line=reader.readLine())!=null){
				content.append(line);
			}
			service=new HandleTextService();
			model.addAttribute("result",service.judge(content.toString()));

		}catch (Exception e){

		}finally {
			try {
				fileUpload.getInputStream().close();
			}catch (Exception e){
			}
		}

	    return "index";
	}


	
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String inputUpload(Model model,String content) {
		model.addAttribute("result",service.judge(content.toString()));
		return "index";
	}






    /*@RequestMapping(value = "/login/loadHeadImage", method = RequestMethod.GET)
	@ResponseBody
	public String loadHeadImage(Result user) {

	}*/
}
