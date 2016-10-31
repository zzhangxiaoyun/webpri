package com.dytt.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Calendar;
public class FileUtils {
	/**
	 * 根据日期获取子目录，若没有就创建
	 * @param root 根目录
	 * @return
	 */
	public static String getSubDirByDay(String root){
		
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder(root)
		.append("/").append(calendar.get(Calendar.MONTH))
		.append("/").append(calendar.get(Calendar.DAY_OF_MONTH))
//		.append("/").append(calendar.get(Calendar.HOUR_OF_DAY))
		.append("/");
		return sb.toString();
	}
	/**
	 * 通过毫秒值和原文件名hash值来返回一个新的文件名
	 * @param oldFilename 老的文件名
	 * @return
	 */
	public static String getNewFileName(String userId,String oldFilename){	
		StringBuilder sb = new StringBuilder(""+userId)
		.append("_")
		.append(System.currentTimeMillis())
		.append(getExt(oldFilename));
		return sb.toString();
	}
	
	private static String getExt(String oldFilename){
		int index = oldFilename.lastIndexOf('.');
		if(index<0)return "";
		return oldFilename.substring(index,oldFilename.length());
	}

	

	/**
	 * copy file
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean write(File source,File target){
		int BUFFER_SIZE = 16 * 1024;
		try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
                in = new BufferedInputStream( new FileInputStream(source), BUFFER_SIZE);
                out = new BufferedOutputStream( new FileOutputStream(target), BUFFER_SIZE);
                 byte [] buffer = new byte [BUFFER_SIZE];
                 int len = -1;
                 while ((len=in.read(buffer)) > 0 )  {
                    out.write(buffer,0,len);
                } 
             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
            return false;
         } 
		return true;
	}
	public static boolean isMatch(String filename,String...exts){
		if(filename==null)return false;
		int extIndex = filename.lastIndexOf(".");
		if(extIndex==-1)return false;
		String ext = filename.substring(extIndex, filename.length());
		for(String e:exts){
			if(e.equals(ext.toLowerCase()))return true;
		}
		return false;
	}
	
	
	/**
	 * 写入
	 * @param text
	 */
	public static void write(File file,String text){
		if(text==null)return;
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
			try {
				fos.write(text.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	/**
	 * 读取文本
	 * @return
	 */
	public static String read(File file){
		StringBuilder sb = new StringBuilder();
			BufferedReader br = null;		
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "";
			}
			
			String str = null;
			try {
				while((str=br.readLine())!=null){
					sb.append(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
			
	}
	
}
