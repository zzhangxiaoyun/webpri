package com.dytt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.dytt.entity.Response;
import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static final int SUCCEED = 0;
	private static final int paramHasNull = 1;
	/**
	 * 返回成功结果
	 * @param obj
	 */
	protected String resSucceed(Object obj,String key) {
		return response(new Response(SUCCEED, "succeed !", obj),key);
	}
	
	protected String resSucceedCache(String cache){
		try {
			HttpServletResponse res = ServletActionContext.getResponse();
			res.setHeader("Content-type", "application/json;charset=UTF-8");  
			res.setCharacterEncoding("utf-8");
			PrintWriter out = res.getWriter();
			out.write(cache);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 返回成功结果
	 * @return
	 */
	protected String resSucceed() {
		return resSucceed(null,null);
	}
	/**
	 * 返回失败信息
	 * @param errCode
	 * @param errMsg
	 */
	protected String resError(int errCode,String errMsg) {
		return response(new Response(errCode, errMsg, null),null);
	}
	/**
	 * 返回 必选参数为空 信息
	 */
	protected String resParamsHasNull(String... params) {
		StringBuilder message = new StringBuilder();
		for(String param:params){
			message.append(" "+param);
		}
		message.append(" 为必填参数，任何一个都不能为空");
		return response(new Response(paramHasNull, message.toString(), null),null);
	}
	
	/**
	 * 返回json数据
	 * @param response
	 * @throws IOException
	 */
	private String response(Response response,String key){
		try {
			HttpServletResponse res = ServletActionContext.getResponse();
			
			res.setHeader("Content-type", "application/json;charset=UTF-8");  
			res.setCharacterEncoding("utf-8");
			PrintWriter out = res.getWriter();
			String str = JSON.toJSONString(response);
			out.write(str);
			out.flush();
			out.close();
			if(key!=null){
				Cache.setCache(key, str);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	/**
	 * 打印日志
	 * @param msg
	 */
	protected void info(String msg){
		Logger.info(this, msg);
	}
}
