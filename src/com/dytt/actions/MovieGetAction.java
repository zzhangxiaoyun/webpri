package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.MyDB;

public class MovieGetAction extends BaseActionSupport {
	private String detailid;
    public String movieGet() {
		boolean flag = MyDB.updateGetMove(detailid);
		if(flag){
			return resSucceed();
		}else {
			return resError(-1,"更新失败");
		}
    }

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}
}
