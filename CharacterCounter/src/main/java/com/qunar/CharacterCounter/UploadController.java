package com.qunar.CharacterCounter;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by jk on 2016/12/24.
 */
@Controller
@RequestMapping(path="/upload")
public class UploadController {
    @RequestMapping(path = "/file",method = RequestMethod.POST)
    //接受前台传递的文件进行分析
    public String  upload(@RequestParam("file")MultipartFile file, Model model) throws IOException {
        FileUtils.writeByteArrayToFile(new File("E:/upload/"+file.getOriginalFilename()),file.getBytes());
        File fileName = new File("E:/upload/"+file.getOriginalFilename());
        CharacterCounter counter = new CharacterCounter(fileName);
        System.out.println(fileName);
        Map res = counter.parse();

        Map chinese = counter.topChinese();
        LinkedList top = (LinkedList) counter.getTop3();
        model.addAttribute("res", res);
        model.addAttribute("chinese",chinese);
        model.addAttribute("top",top);
        model.addAttribute("res", res);
        return "upload";

}
    @RequestMapping(path = "/string",method = RequestMethod.POST)
    //接受前台传递的字符串进行分析
    public String upload(@RequestParam("string")String string ,Model model) throws UnsupportedEncodingException {
        CharacterCounter counter = new CharacterCounter(string);
        Map res = counter.parse();

        Map chinese = counter.topChinese();
        LinkedList top = (LinkedList) counter.getTop3();
        model.addAttribute("res", res);
        model.addAttribute("chinese",chinese);
        model.addAttribute("top",top);
        return "upload";
    }

}


