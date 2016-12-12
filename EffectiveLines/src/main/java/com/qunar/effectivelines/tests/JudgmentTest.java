package com.qunar.effectivelines.tests;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qunar.effectivelines.strategy.Judge;
import com.qunar.effectivelines.strategy.JudgementComment;
import com.qunar.effectivelines.strategy.JudgementEmptyLine;
import com.qunar.effectivelines.strategy.JudgementInterface;

/**
 * @author ryan.hao
 * @version 1.0
 * 判定策略测试
 */
public class JudgmentTest {

    private Logger logger = LoggerFactory.getLogger(JudgmentTest.class);


    @Test
    public void emptyLine() {
        JudgementInterface judgementInterface = new JudgementEmptyLine();
        logger.debug("结果1：{}", judgementInterface.judge(""));
        logger.debug("结果2：{}", judgementInterface.judge("\t\t"));
        logger.debug("结果3：{}", judgementInterface.judge("\t\tint"));
    }

    @Test
    public void comment() {
        JudgementInterface judgementInterface = new JudgementComment();
        logger.debug("结果1：{}", judgementInterface.judge("int"));
        logger.debug("结果2：{}", judgementInterface.judge("\t\t//"));
        logger.debug("结果3：{}", judgementInterface.judge("\t\tint//"));
    }

    @Test
    public void judge() {
        Judge judge = new Judge();
        logger.debug("结果1：{}", judge.judge(""));
        logger.debug("结果2：{}", judge.judge("\t\t"));
        logger.debug("结果3：{}", judge.judge("\t\tint"));
        logger.debug("结果4：{}", judge.judge("int"));
        logger.debug("结果5：{}", judge.judge("\t\t//"));
        logger.debug("结果6：{}", judge.judge("\t\tint//"));
    }

}
