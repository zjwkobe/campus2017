package com.readdy.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.readdy.controller.tools.CharCounter;

/**
 * Created by readdy on 2017/2/7.
 */
public class CharCountingServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        // 执行必需的初始化
        //message = "Hello World";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        // counting
        String text =new String(request.getParameter("text").getBytes("UTF-8"),"UTF-8");

        CharCounter charCounter = new CharCounter();
        charCounter.counting(text);

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println(charCounter.getJsonStr());
    }

    public void destroy() {
        // 什么也不做
    }
}
