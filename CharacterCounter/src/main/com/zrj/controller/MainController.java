package com.zrj.controller;

import com.zrj.model.CountCharacterModel;
import com.zrj.service.CountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by yeluo on 2/6/17.
 */
@Controller
public class MainController {

    private CountService cs = new CountService();
    /**
     * @Author: Zheng Ruijie
     * @Date: 2/6/17 4:57 PM
     * @param: [text, model]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/input_text.do", method = RequestMethod.POST)
    public ModelAndView handleContent(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        CountCharacterModel ccm;
        try {
            String text = new String(request.getParameter("text").getBytes(), "UTF-8");
            ccm = cs.getCharacterCounter(text);
            mav.addObject("countCharacterModel", ccm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ccm = null;
        }
        return mav;
    }

    /**
     * @Author: Zheng Ruijie
     * @Date: 2/6/17 4:58 PM
     * @param: [file, model]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ModelAndView interceptFile(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView("index");
        CountCharacterModel ccm = new CountCharacterModel();
//        response.setContentType("setContentTypetext/html;charset=utf-8");
//        MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
//        MultipartFile file = re.getFile("file");
        if (!file.isEmpty()) {
            String text = new String(file.getBytes(), "UTF-8");
            ccm = cs.getCharacterCounter(text);
        }
        mav.addObject("countCharacterModel", ccm);

        return mav;
    }
}
