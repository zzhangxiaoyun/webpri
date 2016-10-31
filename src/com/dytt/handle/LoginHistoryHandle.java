package com.dytt.handle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dytt.Logger;
import com.dytt.MyDB;
import com.dytt.entity.LoginHistory;

public class LoginHistoryHandle {
	
	private static ExecutorService submit = Executors.newCachedThreadPool();
	
	public static void addLoginHistory(final LoginHistory history){
		submit.submit(new Runnable(){
			@Override
			public void run() {
				MyDB.insertLoginHistory(history);
				Logger.info(LoginHistoryHandle.class, "insert:"+history.toString());
				
			}			
		});		
	}

}
