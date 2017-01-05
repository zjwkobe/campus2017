package com.qunar.ryan.hao.charactercounter.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qunar.ryan.hao.charactercounter.pojo.Result;
import com.qunar.ryan.hao.charactercounter.service.ICharacterCounterService;
import com.qunar.ryan.hao.charactercounter.utils.FileUtils;

@Controller
@RequestMapping("/")
public class CharacterCounterController {

    Logger logger = LoggerFactory.getLogger(CharacterCounterController.class);

    @Autowired
    private ICharacterCounterService characterCounterService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "charactercounter";
	}

    /**
     * 使用文件上传的方式统计字符
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/countbyfile", method = RequestMethod.POST)
    public Map<String, Object> countByFile(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        logger.info("处理文件上传字符统计请求");
        Map<String, Object> rt = new HashMap<String, Object>();
        String fileName = null;
        try {
            fileName = FileUtils.saveUploadFile(file, request);
            logger.info("保存正在上传的文件，成功，文件名：{}", fileName);
            Result result = characterCounterService.countByFile(fileName, request);
            logger.info("字符统计完成，结果：{}", result);
            rt.put("status", "success");
            rt.put("result", result);
        } catch (Exception e) {
            logger.error("处理文件上传字符统计请求出现异常：{}", e.getMessage(), e);
            rt.put("status", "error");
        } finally {
            if (fileName != null) {
                if (FileUtils.deleteUploadFile(fileName, request)) {
                    logger.info("文件清理完成，文件名：{}", fileName);
                } else {
                    logger.error("文件删除出现异常，文件名：{}", fileName);
                }
            } else {
                logger.error("由于之前的异常，没有文件被清理");
            }
        }
        return rt;
    }

    /**
     * 使用手动填写的方式统计字符
     * @param input_text
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/countbyinput", method = RequestMethod.POST)
    public Map<String, Object> countByInput(String input_text) {
        logger.info("处理手动填写字符统计请求");
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            List<String> content = new LinkedList<String>();
            content.add(input_text);
            Result result = characterCounterService.countByContent(content);
            logger.info("字符统计完成，结果：{}", result);
            rt.put("status", "success");
            rt.put("result", result);
        } catch (Exception e) {
            logger.error("处理手动填写文本字符统计请求出现异常：{}", e.getMessage(), e);
            rt.put("status", "error");
        }
        return rt;
    }

}