package com.java;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.java.statis;

import java.io.PrintWriter;

/**
 * Created by GL on 2017/2/9.
 */
@Controller
public class hello {
    private statis res;
    @Autowired
    public hello(statis res){
        this.res = res;
    }
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public void upload(@RequestParam(required = false)String content, PrintWriter out) {
        String result = null;
        result = res.getResult(content).toString();
        out.write(result);
    }

}
