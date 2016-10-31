package com.dytt.actions;
import java.util.ArrayList;
import java.util.List;

import com.dytt.BaseActionSupport;
import com.dytt.MyDB;
import com.dytt.entity.Config;
import com.dytt.entity.LoginHistory;
import com.dytt.entity.StartEntity;
import com.dytt.handle.LoginHistoryHandle;
import com.dytt.utils.TimeHelper;
public class StartAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	public static List<String> ds;
	private String deviceId;
    public String getStart() {
    	//插入历史记录
    	LoginHistoryHandle.addLoginHistory(new LoginHistory(deviceId,TimeHelper.format(System.currentTimeMillis())));
    	
    	boolean upload;
    	int num,deviceIdsNum;
    	String key = null;//"getStart";
    	ds = new ArrayList<String>();
    	    Config config =  MyDB.getConfig();
    	    if(config==null){
    	    	config = new Config();
    	    }
    		String didsStr = config.getTargetids();
    		for(String str:didsStr.split(",")){
    			if(str.length()>0){
    				ds.add(str);
    			}
    		}
    		upload = config.isUpload();
    		num = config.getNum();
    		deviceIdsNum = config.getTargetidnum();
    	if(ds.contains(deviceId)){
    		return resSucceed(new StartEntity(true,deviceIdsNum,config.getShowsendversion()),key);
    	}
    	return resSucceed(new StartEntity(upload,num,config.getShowsendversion()),key);
    }
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
