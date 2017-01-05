package com.qunar.ryan.hao.charactercounter.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qunar.ryan.hao.charactercounter.pojo.Result;

/**
 * Created by ryan.hao on 16-12-15.
 */
public interface ICharacterCounterService {

    Result countByFile(String fileName, HttpServletRequest request);

    Result countByContent(List<String> content);
}
