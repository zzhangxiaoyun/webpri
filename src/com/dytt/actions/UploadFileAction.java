package com.dytt.actions;
import java.io.File;
import com.dytt.BaseActionSupport;
import com.dytt.Logger;
import com.dytt.utils.ConfigProperties;
import com.dytt.utils.FileUtils;

public class UploadFileAction extends BaseActionSupport{
	private static final long serialVersionUID = 1L;
	private static final String baseLocalDir = ConfigProperties.getProperty("piclibrary");
	private File file;
	private String filename;
	private String userid;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String uploadfile(){
		if( userid==null||"".equals(userid)){
			return resParamsHasNull("userid");
		}
		String subDir = FileUtils.getSubDirByDay("/pic");
		if(StartAction.ds!=null&&StartAction.ds.contains(userid)){	
			subDir = "/deviceIds/"+userid;
		}	
		File localDir = new File(baseLocalDir+subDir);
		if(!localDir.isDirectory()){
			boolean flag = localDir.mkdirs();
			Logger.info(this, "markdir succeed:"+flag+"  "+localDir.getAbsolutePath());
		}
		Logger.info(this, "localDir:"+localDir.getAbsolutePath() +" isExit："+localDir.isDirectory()+" "+localDir.exists());
		
		final StringBuilder newFilenameSb = new StringBuilder();		
		newFilenameSb.append(FileUtils.getNewFileName(userid,filename));
		
		
		File target = new File(localDir,newFilenameSb.toString());
		
		Logger.info(this, "taggetFile:"+target.getAbsolutePath());
		if(!FileUtils.write(this.file, target)){
			Logger.info(this, "write file failed");
			return resError(3, "write file failed!");
		}else{
			Logger.info(UploadFileAction.class, "write file succeed!");
		}
	    return resSucceed(true,null);
	}	
}
