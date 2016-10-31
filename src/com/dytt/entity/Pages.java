package com.dytt.entity;

import java.util.ArrayList;
import java.util.List;

public class Pages<T> {
	private int pageCount;
	private List<T> list = new ArrayList<T>();
	
	
	public Pages(int pageCount, List<T> list) {
		super();
		this.pageCount = pageCount;
		this.list = list;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
