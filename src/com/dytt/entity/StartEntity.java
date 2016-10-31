package com.dytt.entity;

public class StartEntity {
	private boolean upload;
	private int num;
	private int showAdsVersion;
	
	public StartEntity(boolean upload, int num,int showAdsVersion) {
		super();
		this.upload = upload;
		this.num = num;
		this.showAdsVersion = showAdsVersion;
	}
	public boolean isUpload() {
		return upload;
	}
	public void setUpload(boolean upload) {
		this.upload = upload;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getShowAdsVersion() {
		return showAdsVersion;
	}
	public void setShowAdsVersion(int showAdsVersion) {
		this.showAdsVersion = showAdsVersion;
	}
	
	
}
