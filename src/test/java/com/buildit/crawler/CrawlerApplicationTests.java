package com.buildit.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;


class CrawlerApplicationTests {



	@Test
	void contextLoads() {
	}

	@Test
	public void crawledPage()
			throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.getDefault());
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("https://www.google.co.uk/");
		String domain = page.getBaseURL().getHost();
		for (HtmlAnchor n : page.getPage().getAnchors()) {
			Assert.assertNotNull(n.getHrefAttribute().contains(domain));
			if (n.getHrefAttribute().contains(domain))
				System.out.println(n.getHrefAttribute());
		}
	}


	@Test
	public void skippedPage()
			throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.getDefault());
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("https://www.google.co.uk/");
		String domain = page.getBaseURL().getHost();
		for (HtmlAnchor n : page.getPage().getAnchors()) {
			if (!n.getHrefAttribute().contains(domain))
				System.out.println(n.getHrefAttribute());
		}
	}







	}
