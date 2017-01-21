package com.qunar.controller;

import com.qunar.model.CountResultModel;
import com.qunar.service.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * CharacterCounter Controller
 *
 */
@Controller
@RequestMapping("/counter")
public class CounterController {


    @RequestMapping("/test.do")
    public String test(){

        log("/counter/test");


        return "result";

    }



    /**
     * 拦截以文本方式发送的请求
     * @param text
     * @param model
     * @return
     */
    @RequestMapping(value="/input.do",method = RequestMethod.POST)
    public String readTextInput(@RequestParam("text") String text,Model model){

        log("/counter/input.do");

        CounterService service = new CounterService();
        CountResultModel crm = service.count(text);
        model.addAttribute(crm);

        return "result";

    }

    /**
     * 拦截以文件上传方式发送的请求
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file,Model model)  throws IOException {

        log("/counter/upload.do ");

        if(!file.isEmpty()){
            String text = new String(file.getBytes(),"UTF-8");
            CounterService service = new CounterService();
            CountResultModel crm = service.count(text);
            model.addAttribute(crm);
        }else{
            CountResultModel crm = new CountResultModel();
            model.addAttribute(crm);
        }

        return "result";

    }

    public static void log(String message){
        System.out.println(new Date().toLocaleString() + " Message:" + message);
    }

}
