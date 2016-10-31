package com.dytt.entity;

public class LoginHistory {
	private int id;
	private String deviceId;
	private String loginTime;
	
	public LoginHistory() {
		super();
	}
	public LoginHistory(String deviceId, String loginTime) {
		super();
		this.deviceId = deviceId;
		this.loginTime = loginTime;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"	"+deviceId+"	"+loginTime;
	}

}
