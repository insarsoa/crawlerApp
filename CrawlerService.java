package com.buildit.crawler;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CrawlerService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerService.class);

	private final String[] protocols = {"http","https"}; //supporting only http and https
	private final UrlValidator urlValidator = new UrlValidator(protocols);

	private static ConcurrentHashMap<String, Webpage> pages = new ConcurrentHashMap<>();

	public void generateSitemap(String websiteBaseUrl, boolean followExternalLinks) {
		// Extensions
		// 1. Alert on non-SSL links
		// 2. Highlight non HTTP links
		// 3. Highlight dead links
		// 4. Measure time to retrieve

		// Validate and proceed...
		if (isWebsiteValid(websiteBaseUrl)) {
			try {
				URL urlAddress = new URL(websiteBaseUrl);
				String domain = urlAddress.getHost();
				getLinks(websiteBaseUrl, null, followExternalLinks, domain);
			}catch (MalformedURLException e) {
				LOGGER.error("For '" + websiteBaseUrl + "': " + e.getMessage());
			}
		}
	}

	private void getLinks(String linkedUrl, String parentUrl, boolean followExternalLinks, String Domain) {
			try {
				Webpage currentPage;
				Webpage parentPage; 
				
				if(pages.get(linkedUrl) != null) {
					LOGGER.info("Found crawled page {} skipping", linkedUrl);
					return;
				}
				
				if(StringUtils.hasText(parentUrl)) {
					parentPage = pages.get(parentUrl);	
					if(parentPage == null) {
						throw new IllegalStateException("unknown page found while crawling without being linked to : "+ parentUrl);
					}
					currentPage = pages.getOrDefault(linkedUrl, new Webpage(linkedUrl, parentPage));
				} else {
					currentPage = pages.getOrDefault(linkedUrl, new Webpage(linkedUrl));
				}
				
				if(!linkedUrl.contains(Domain) && !followExternalLinks) {
					LOGGER.info("Found external link to {} and followExternalLinks is {}, ignoring", linkedUrl,  followExternalLinks);
					return;
				}
				Document document = Jsoup.connect(linkedUrl).get();
				LOGGER.info(document.title());
				LOGGER.info("Crawled {}", linkedUrl);
				Elements linksOnPage = document.select("a:not([href^=#]").select("a[href~=^/?[^/]+]");
				pages.put(linkedUrl, currentPage);
				for (Element page : linksOnPage) {
					String link = page.attr("href");
					Webpage linkedPage = pages.getOrDefault(link, new Webpage(link, currentPage));
					pages.putIfAbsent(link, linkedPage);
					
					getLinks(link, linkedUrl, followExternalLinks, Domain);
				}
				pages.put(linkedUrl, currentPage);
				
			} catch (IOException e) {
				LOGGER.error("For '" + linkedUrl + "': " + e.getMessage());
			}
	}

	private boolean isWebsiteValid(String url){
			if (urlValidator.isValid(url)) {
				return true;
			}
			return false;
		}

}
