package com.yuanhaolv.api;

import com.yuanhaolv.model.Area;
import com.yuanhaolv.model.Exchange;

import java.io.IOException;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
public interface ICrawData {
    /**
     *
     *   Description: 爬取数据
     *   @authour 元浩
     *   @date 2016/12/14
     *
     *   @param area 需要爬取的地区/国家
     *   @return List<Exchange> 过去30天该地区/国家的汇率情况
     *   @throws IOException
     *   @since
     **/
    List<Exchange> crawData(Area area) throws IOException;

}
