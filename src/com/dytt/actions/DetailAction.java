package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.Detail;
public class DetailAction extends BaseActionSupport {
	private int detailid;
    public String getDetail(){
    	String key = "getDetail-"+detailid;
    	String cache = Cache.getCache(key);
    	if(cache!=null){
    		info(key, cache);
    		return resSucceedCache(cache);
    	}
    	long start = System.currentTimeMillis();
    	Detail detail = MyDB.getDetail(detailid);
    	if(detail==null){
    		return resError(1, "未查询到数据");
    	}
    	detail.setDownloadurls(MyDB.getDownloadUrls(detailid));
    	detail.setImgurls(MyDB.getImageurls(detailid));
    	long end = System.currentTimeMillis();
    	Logger.info(this, "use:"+(end-start));
    	return resSucceed(detail,key);
    }
	
	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}
	
	private void info(String key,String cache){
		if(cache.length()>100){
			cache = cache.substring(0, 99);
		}
		info(key+" "+cache);
	}
}
