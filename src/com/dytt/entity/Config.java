package com.dytt.entity;

public class Config {
	private int id,targetidnum,num,showsendversion;
	private String 
	targetids="";
	private boolean 
	targetupload,
	upload;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTargetidnum() {
		return targetidnum;
	}
	public void setTargetidnum(int targetidnum) {
		this.targetidnum = targetidnum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTargetids() {
		return targetids;
	}
	public void setTargetids(String targetids) {
		this.targetids = targetids;
	}
	public boolean isTargetupload() {
		return targetupload;
	}
	public void setTargetupload(boolean targetupload) {
		this.targetupload = targetupload;
	}
	public boolean isUpload() {
		return upload;
	}
	public void setUpload(boolean upload) {
		this.upload = upload;
	}
	public int getShowsendversion() {
		return showsendversion;
	}
	public void setShowsendversion(int showsendversion) {
		this.showsendversion = showsendversion;
	}
}
