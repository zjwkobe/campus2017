package com.wordcount.controller;

import com.wordcount.disruptor.MultipartFileEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by admin on 2017/1/6.
 */
@Controller
public class IndexController {

    @Autowired
    private MultipartFileEventHandler handler;

    @RequestMapping(value = "/wordCount",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String wordCount(@RequestParam(value = "file",required = false) MultipartFile file) {
        System.out.println(file);
        if(file==null){
            return "{}";
        }else{
            List<String> results = handler.onEvent(file);
            StringBuilder builder = new StringBuilder("[");
            for(String parm:results){
                builder.append(parm+",");
            }
            builder.delete(builder.length()-1,builder.length());
            builder.append("]");
            return builder.toString();
        }
    }

    @RequestMapping(value = "/textWordCount",method ={RequestMethod.POST})
    @ResponseBody
    public String wordCountByText(@RequestParam(value = "textarray")  String text){
        System.out.println("-------------");
        System.out.println(text);
        System.out.println("-------------");
        if(text == null){
            return null;
        }else{
            List<String> results = handler.onEvent(text);
            StringBuilder builder = new StringBuilder("[");
            for(String parm:results){
                builder.append(parm+",");
            }
            builder.delete(builder.length()-1,builder.length());
            builder.append("]");
            return builder.toString();
        }
    }

    @RequestMapping("/")
    public String wordCount(){
        return "/index.html";
    }
}
