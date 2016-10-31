package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.MyDB;

public class FeedbackAction extends BaseActionSupport {
	private String text,deviceId;
    public String feedback() {
    	boolean flag = false;
    	if(text!=null&&text.length()>0&&text.length()<1000){
    		flag = MyDB.addFeedback(text);    		
    	}
    	return resSucceed(flag,null);
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
    
	
	
}
