package com.baseproject;

import java.util.TimeZone;

import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.baseproject.init.service.InitService;
@EnableAsync
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

	protected final Log log = LogFactory.getLog(MainApplication.class);

	@Autowired
	Environment env;

	@Autowired
	InitService initService;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MainApplication.class);
	}

	@PostConstruct
	void init() {
		log.info("##### ENVIRONMENT " + env.getProperty("environment") + " #####");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		initService.init();
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.initialize();
		return executor;
	}

}
