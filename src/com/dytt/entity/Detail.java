package com.dytt.entity;

import java.util.ArrayList;
import java.util.List;

public class Detail {
	private int id;
	private String time;	
	private String name;
	private String content;
    private List<String> downloadurls = new ArrayList<String>();
    private List<String> imgurls = new ArrayList<String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getDownloadurls() {
		return downloadurls;
	}
	public void setDownloadurls(List<String> downloadurls) {
		this.downloadurls = downloadurls;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<String> getImgurls() {
		return imgurls;
	}
	public void setImgurls(List<String> imgurls) {
		this.imgurls = imgurls;
	}
}
