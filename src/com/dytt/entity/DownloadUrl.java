package com.dytt.entity;

public class DownloadUrl {
	private int id;
	private int detailId;
	private String downloadUrl;
	public DownloadUrl(int detailId, String downloadUrl) {
		super();
		this.detailId = detailId;
		this.downloadUrl = downloadUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	@Override
	public String toString() {
		return "DownloadUrl [id=" + id + ", detailId=" + detailId
				+ ", downloadUrl=" + downloadUrl + "]";
	}
	

}
