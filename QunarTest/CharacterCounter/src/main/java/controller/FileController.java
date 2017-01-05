package controller;

import model.FileMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.AnalyseFile;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuxin.qin on 2017/1/3.
 */
@Controller
public class FileController {

    @Resource
    AnalyseFile analyseFile;

    @RequestMapping(value="/getMessageInfo", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView getInfo(String str)
    {
        FileMessage fileMessage = new FileMessage();
        fileMessage=analyseFile.analyse(str);
        Map<String,Object> reasult = new HashMap<String,Object>();
        reasult.put("map",fileMessage);
        return new ModelAndView("/index",  reasult);
    }

}
