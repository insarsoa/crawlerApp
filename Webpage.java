package com.buildit.crawler;

import java.util.HashSet;
import java.util.Set;

public class Webpage {

	private String url;
	private Webpage parent;
	private Set<Webpage> links = new HashSet<>();

	public Webpage(String url) {
		this.url = url;
	}

	public Webpage(String url, Webpage parent) {
		this.url = url;
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Webpage getParent() {
		return parent;
	}

	public void setParent(Webpage parent) {
		this.parent = parent;
	}

	public Set<Webpage> getLinks() {
		return links;
	}

	public void setLinks(Set<Webpage> links) {
		this.links = links;
	}
	
	@Override
	public String toString() {
		return this.url;
	}

}
