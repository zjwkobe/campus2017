package com.yuanhaolv.controller;

import com.yuanhaolv.api.ICrawData;
import com.yuanhaolv.model.Area;
import com.yuanhaolv.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Controller("crawController")
public class CrawController {
    @Autowired
    ICrawData crawData;

    public List<Exchange> crawData(Area area) throws IOException{
        List<Exchange> result = crawData.crawData(area);
        return result;
    }
}
