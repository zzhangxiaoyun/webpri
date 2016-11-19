package com.dytt.entity;

/**
 * Created by zhangxiaoyun01 on 2016/11/18.
 */
public class MovieGetInfo {
    private int id;
    private String detailId;
    private int count;

    public MovieGetInfo() {
    }

    public MovieGetInfo(int id, String detailId, int count) {
        this.id = id;
        this.detailId = detailId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
