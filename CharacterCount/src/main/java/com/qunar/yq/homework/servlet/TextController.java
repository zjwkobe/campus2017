package com.qunar.yq.homework.servlet;

import com.alibaba.fastjson.JSONObject;
import com.qunar.yq.homework.entity.Result;
import com.qunar.yq.homework.service.TextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by qiaoy.yang on 2016/12/29.
 */
@RequestMapping("/count")
@Controller
public class TextController {
    private static Logger logger = LoggerFactory.getLogger("Result: ");

    private TextService textService = new TextService();

    /**
     * 处理文本
     *
     * @param text
     * @return
     */
    @RequestMapping("/text")
    public
    @ResponseBody
    String process(String text) {
        Result result = textService.process(text);
        String s = JSONObject.toJSON(result).toString();
        logger.info(s);
        return s;
    }

    /**
     * 处理文件上传
     *
     * @param file
     * @return
     */

    @RequestMapping("/file")
    public
    @ResponseBody
    String process(MultipartFile file) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            Result result = textService.process(new String(sb.toString().getBytes(), "UTF-8"));

            String s = JSONObject.toJSON(result).toString();
            logger.info(s);
            return s;
        } catch (Exception e) {
            Result result = new Result();
            result.setMeg("系统错误");
            result.setStatus(-1);
            String s = JSONObject.toJSON(result).toString();
            logger.info(s);
            return s;
        }
    }

}
