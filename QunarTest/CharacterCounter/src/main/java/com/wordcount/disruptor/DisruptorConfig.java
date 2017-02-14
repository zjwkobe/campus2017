package com.wordcount.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2017/1/10.
 */
@Configuration
public class DisruptorConfig {

    MultipartFileEventHandler handler = new MultipartFileEventHandler();

    @Bean
    public MultipartFileEventHandler getDisruptor() {
        return handler;
    }
}
