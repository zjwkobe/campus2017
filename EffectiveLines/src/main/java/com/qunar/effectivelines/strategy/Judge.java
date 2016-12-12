package com.qunar.effectivelines.strategy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ryan.hao
 * @version 1.0
 * 代码行判定类
 */
public class Judge {

    /**
     * 日至
     */
    private Logger logger = null;

    /**
     * 判定策略
     */
    private List<JudgementInterface> strategy = null;

    public Judge() {
        logger = LoggerFactory.getLogger(Judge.class);
        strategy = new ArrayList<JudgementInterface>();
        initStrategy();
    }

    /**
     * 初始化策略集合
     */
    private void initStrategy() {
        logger.debug("开始初始化判定策略");
        strategy.add(new JudgementEmptyLine());
        strategy.add(new JudgementComment());
    }

    /**
     * 判定代码行是否为有效行
     * @param line 代码行
     * @return true为有效行，false为无效行
     */
    public boolean judge(String line) {
        logger.debug("判定行：{}", line);
        for (JudgementInterface item : strategy) {
            if (!item.judge(line)){
                logger.debug("结果：无效行");
                return false;
            }
        }
        logger.debug("结果：有效行");
        return true;
    }

}
