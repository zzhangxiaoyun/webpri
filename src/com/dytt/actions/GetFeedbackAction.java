package com.dytt.actions;

import java.util.List;

import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.Feedback;
import com.dytt.entity.Pages;

public class GetFeedbackAction extends BaseActionSupport {
	private int pagesize = 25;
	private int pageindex = 0;
    public String getFeedback() {
    	String key = "getFeedback-"+pagesize+"-"+pageindex;;
    	String cache = Cache.getCache(key);
    	if(cache!=null){
    		return resSucceedCache(cache);
    	}
    	long start = System.currentTimeMillis();
    	Logger.info(this, "start pageIndex:"+pageindex+" "+pagesize);
    	List<Feedback> infos = MyDB.getFeedback(pagesize, pageindex);
    	
    
    	int totalPageNum = 0;
    	String totalKey = "getFeedbackCount";
    	String totalCache = Cache.getCache(totalKey);
    	if(totalCache!=null){
    		totalPageNum = Integer.valueOf(totalCache);    			
    	}else{
    		totalPageNum = ( MyDB.getFeedbackCount()  +  pagesize  - 1) / pagesize; 
    		Cache.setCache(totalKey, totalPageNum+"");
    	}
    	long end = System.currentTimeMillis();
    	Logger.info(this, "use:"+(end-start));
    	return resSucceed(new Pages(totalPageNum,infos),key);
    }
    
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
}
