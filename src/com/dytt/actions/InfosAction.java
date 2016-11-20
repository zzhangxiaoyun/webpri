package com.dytt.actions;

import java.util.List;

import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.InfoSimple;
import com.dytt.entity.Pages;

public class InfosAction extends BaseActionSupport {
	private int pagesize = 25;
	private int pageindex = 0;
	private String type;
	private int year;
	private int haspic;
    public String getInfoList() {
		if("hot".equals(type)){
			return getHotList();
		}else {
			return getNomaiInfoList();
		}
    }
    private String getNomaiInfoList(){
		long start = System.currentTimeMillis();
		Logger.info(this, "start pageIndex:"+pageindex+" "+pagesize);
		List<InfoSimple> infos = MyDB.getInfoSimples(pagesize, pageindex, type, year, haspic);
		int totalPageNum = ( MyDB.getDetailCount(type,year)  +  pagesize  - 1) / pagesize;
		long end = System.currentTimeMillis();
		Logger.info(this, "use:"+(end-start));
		return resSucceed(new Pages(totalPageNum,infos),null);
	}

	private String getHotList() {
		long start = System.currentTimeMillis();
		Logger.info(this, "start pageIndex:"+pageindex+" "+pagesize);
		List<InfoSimple> infos = MyDB.getHotInfoSimples(pagesize, pageindex, year, haspic);
		int totalPageNum = ( MyDB.getHotCount(year)  +  pagesize  - 1) / pagesize;
		long end = System.currentTimeMillis();
		Logger.info(this, "use:"+(end-start));
		return resSucceed(new Pages(totalPageNum,infos),null);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHaspic() {
		return haspic;
	}

	public void setHaspic(int haspic) {
		this.haspic = haspic;
	}
}
