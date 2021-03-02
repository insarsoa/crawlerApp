package com.buildit.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:application.properties"}, ignoreResourceNotFound = true)
@PropertySource(value = { "file:./conf/application.properties"}, ignoreResourceNotFound = true)
public class CrawlerConfig {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerConfig.class);

	@Autowired
	Environment env;

	public CrawlerConfig() {
		LOGGER.debug("CrawlerConfig created !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
}
