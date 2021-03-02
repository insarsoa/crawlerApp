package com.buildit.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class CrawlerApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerApplication.class);
	
	@Autowired
	private CrawlerService crawlerService;

	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		LOGGER.info("EXECUTING : command line runner");

		for (int i = 0; i < args.length; ++i) {
			LOGGER.info("args[{}]: {}", i, args[i]);

		}
		if(args == null || args.length <=0 || args.length > 2) {
			LOGGER.info("This application requires two arguments : website base url (should be a URL and is mandatory) and followExternalLinks (boolean, optional, default is false)");
		}
		boolean followExternalLinks = false;
		if(args.length == 2) {
			followExternalLinks = StringUtils.hasText(args[1]) ? ("true".equalsIgnoreCase(args[1]) ? true : false) : false;
		}
				 
		crawlerService.generateSitemap(args[0], followExternalLinks);
	}

}
