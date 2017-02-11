package com.hk__lrzy.homework.Controller;

import com.hk__lrzy.homework.Model.Article;
import com.hk__lrzy.homework.Util.CharacterCountUtil;
import com.hk__lrzy.homework.Util.UploadContentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hk__lrzy on 2017/2/10.
 */
@Controller
public class IndexController {

    /**
     * 跳转到目录页面
     * @param view
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(ModelAndView view){
        view.setViewName("index");
        return view;
    }

    /*@RequestMapping("/countFile")
    public String countFile(MultipartFile uploadContent){
        System.out.println(uploadContent);
        String content = null;
        if (!uploadContent.isEmpty()){
            System.out.println("zhuanhuan");
            content = .readUploadContent(uploadContent);
        }
        System.out.println(content);

        return count(content);
    }*/

    /*或者使用PrintWriter 还有使用@ResponseBody的一个特别之处就是，在使用此注解之后不会再走返回视图处理器路径，而是直接将数据写入到输入流中，
等同于使用response.getWriter().print(data)输出到页面上;*/

    @RequestMapping("/count")
    public @ResponseBody Map count(@RequestParam(name = "content",required = false) String content,@RequestParam(name="uploadContent",required = false) MultipartFile uploadContent){
        System.out.println(content + "   " + uploadContent);
        if (content == null){
            content = UploadContentUtil.readUploadContent(uploadContent);
        }
        Article article = CharacterCountUtil.analyseContent(content);
        HashMap res= new HashMap<>();
        HashMap classify = new HashMap();
        classify.put("英文字母",article.getCharacter());
        classify.put("数字",article.getNumber());
        classify.put("中文汉字",article.getChinese());
        classify.put("中英文标点符号",article.getSymbol());
        res.put("classify",classify);
        res.put("rank",article.getRank());
        Map map = new HashMap();
        map.put("success",res);
        return map;

    }


}
