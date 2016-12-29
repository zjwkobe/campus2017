package com.qtree;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Wang on 2016/12/18.
 */
@Controller
public class HomeController {

    @RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value="/uploadFile",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public   @ResponseBody String ajaxFileData(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request, HttpServletResponse response)
    {
//       //获取绝对路径
//        String rootpath = request.getSession().getServletContext().getRealPath("");
//        String fileName = file.getOriginalFilename();
//        String path="/static/file/"+fileName;
//        File targetFile = new File(rootpath,path);
//        if(!targetFile.exists()){
//            targetFile.mkdirs();
//        }else{
//            targetFile.delete();
//        }
//        //保存
//        try {
//            file.transferTo(targetFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        StringBuilder sb=new StringBuilder();
//        try {
//            BufferedReader br=new BufferedReader(new UnicodeReader(file.getInputStream(), "UTF-8"));
//            String str;
//            while((str = br.readLine()) != null) {
//                sb.append(str);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        return CharacterCounter.getInstance().count(file).toJsonString();
    }


    @RequestMapping(value="/uploadText",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public   @ResponseBody String ajaxText(@RequestParam(value = "text") String text,HttpServletRequest request, HttpServletResponse response)
    {
        return CharacterCounter.getInstance().count(text).toJsonString();
    }
}
