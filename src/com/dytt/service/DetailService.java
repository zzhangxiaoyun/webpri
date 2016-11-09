package com.dytt.service;

import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.Detail;

/**
 * Created by zhangxiaoyun01 on 2016/11/9.
 */
public class DetailService {

    public static Detail getDetail(int detailid){
        long start = System.currentTimeMillis();
        Detail detail = MyDB.getDetail(detailid);
        if(detail==null){
            return null;
        }
        detail.setDownloadurls(MyDB.getDownloadUrls(detailid));
        detail.setImgurls(MyDB.getImageurls(detailid));
        long end = System.currentTimeMillis();
        Logger.info(DetailService.class, "use:"+(end-start));
        return detail;
    }
}
