package com.dytt.entity;

public class Response {
	private int returncode;
	private String message;
	private Object result;
	public Response(int returncode, String message, Object result) {
		super();
		this.returncode = returncode;
		this.message = message;
		this.result = result;
	}
	public int getReturncode() {
		return returncode;
	}
	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
