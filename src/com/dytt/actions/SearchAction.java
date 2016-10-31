package com.dytt.actions;

import java.util.List;

import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.InfoSimple;
import com.dytt.entity.Pages;

public class SearchAction extends BaseActionSupport {
	private int pagesize = 25;
	private int pageindex = 0;
	private String searchKey;

    /**
     * 搜索
     * @return
     */
    public String searchInfoList() {
    	String key = "searchInfoList-"+pagesize+"-"+pageindex+"-"+searchKey;
    	String cache = Cache.getCache(key);
    	if(cache!=null){
    		return resSucceedCache(cache);
    	}
    	long start = System.currentTimeMillis();
    	Logger.info(this, "start search pageIndex:"+searchKey+"  "+pageindex+" "+pagesize);
    	List<InfoSimple> infos = MyDB.searchInfoSimples(pagesize, pageindex, searchKey);
    	
    	int totalPageNum = ( MyDB.searchDetailCount(searchKey)  +  pagesize  - 1) / pagesize; 
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
