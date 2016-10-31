package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.MyDB;

public class ContentAction extends BaseActionSupport {
	private int detailid;
    public String getDetailContent(){
    	String key = "getDetailContent-"+detailid;
    	String cache = Cache.getCache(key);
    	if(cache!=null){
    		return resSucceedCache(cache);
    	}
    	String content = MyDB.getDetailContent(detailid);
    	if(content==null){
    		resError(-1, "未获取到数据");
    	}
    	return resSucceed(content,key);
    }
	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}
}
