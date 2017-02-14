package com.wordcount;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.wordcount.disruptor.MultipartFileEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
public class WordcountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordcountApplication.class, args);//运行主程序
		//test();
	}
}
