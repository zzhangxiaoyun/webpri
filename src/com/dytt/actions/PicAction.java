package com.dytt.actions;

import com.dytt.BaseActionSupport;
import com.dytt.Logger;
import com.dytt.utils.ConfigProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PicAction extends BaseActionSupport {
	private static final String baseLocalDir = ConfigProperties.getProperty("piclibrary");
    public String getPics(){
        List<String> files = new ArrayList<>();
		getDirectory(new File(baseLocalDir), files);
    	return resSucceed(files, null);
    }

    private void getDirectory(File file,List<String> files) {
        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
            return;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                Logger.info(this,"Dir==>" + f.getAbsolutePath());
                getDirectory(f,files);
            } else {
                files.add(f.getAbsolutePath());
                Logger.info(this,"file==>" + f.getAbsolutePath());
            }
        }
    }

}
