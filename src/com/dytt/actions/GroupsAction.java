package com.dytt.actions;
import com.dytt.BaseActionSupport;
import com.dytt.Cache;
import com.dytt.MyDB;

public class GroupsAction extends BaseActionSupport {
    public String getGroups(){
    	String key = "getGroups";
    	String cache = Cache.getCache(key);
    	if(cache!=null){
    		return resSucceedCache(cache);
    	}
    	return resSucceed(MyDB.getGroups(),key);
    }
}
