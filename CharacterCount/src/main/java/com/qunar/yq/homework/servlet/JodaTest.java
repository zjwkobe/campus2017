package com.qunar.yq.homework.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qiaoy.yang on 2016/12/29.
 */
@RequestMapping("/")
@Controller
public class JodaTest {

    @RequestMapping("/test")
    public String demo1(){
        System.out.println("----------------------------");
       return "index";
    }


}
