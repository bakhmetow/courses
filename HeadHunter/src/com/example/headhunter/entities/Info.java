package com.example.headhunter.entities;

import java.util.ArrayList;
import java.util.List;

public class Info {
	private Object clusters;
	private List<Item> items = new ArrayList<Item>();
	private Integer found;
	private Integer per_page;
	private Integer page;
	private Integer pages;

	public Object getClusters() {
		return clusters;
	}

	public void setClusters(Object clusters) {
		this.clusters = clusters;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getFound() {
		return found;
	}

	public void setFound(Integer found) {
		this.found = found;
	}

	public Integer getPer_page() {
		return per_page;
	}

	public void setPer_page(Integer per_page) {
		this.per_page = per_page;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
}
