package com.dytt.entity;

public class Feedback {
	private int id;
	private String text;
	private String time;
	public Feedback() {
	}
	public Feedback(String text, String time) {
		super();
		this.text = text;
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
