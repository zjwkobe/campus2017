package com.qunar.ryan.hao.charactercounter.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qunar.ryan.hao.charactercounter.pojo.Result;
import com.qunar.ryan.hao.charactercounter.utils.AlgorithmUtils;
import com.qunar.ryan.hao.charactercounter.utils.FileUtils;

/**
 * Created by ryan.hao on 16-12-15.
 */
@Service
public class CharacterCounterService implements ICharacterCounterService {

    @Override
    public Result countByFile(String fileName, HttpServletRequest request) {
        List<String> list = FileUtils.readUploadFile(fileName, request);
        return countByContent(list);
    }

    @Override
    public Result countByContent(List<String> lines) {
        return AlgorithmUtils.characterCount(lines);
    }
}
