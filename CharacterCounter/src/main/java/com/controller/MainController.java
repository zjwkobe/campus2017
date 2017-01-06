package com.controller;

import com.model.Count;
import com.statics.StaticsString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/1/6.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/countchar",produces = "text/html;charset=UTF-8")
    public String count(@RequestParam("text") String text, @RequestParam("identify") String s, Model model) throws UnsupportedEncodingException {
        if (s.equals("cl")){
            model.addAttribute("count",null);
            model.addAttribute("top3",null);
            model.addAttribute("text",null);
        }
        if (s.equals("co")) {
            StaticsString staticsString = new StaticsString(text);
            Count count = staticsString.countCharacters();

            String[] strings=staticsString.getTop3Char();
            Integer[] integers=staticsString.getTop3Int();

            model.addAttribute("count", count);
            model.addAttribute("strings", strings);
            model.addAttribute("integers",integers);
            model.addAttribute("text", text);
        }
        return "index";
    }
}
