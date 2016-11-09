package test;

import com.oreilly.servlet.Base64Encoder;
import com.sun.deploy.net.URLEncoder;
import sun.misc.BASE64Encoder;

/**
 * Created by zhangxiaoyun01 on 2016/11/9.
 */
public class Test {

    public static void main(String[] args)throws Exception{
        String p = "20";

        String str = Base64Encoder.encode(p);
        str = URLEncoder.encode(str,"utf-8");
        System.out.print(str);

    }
}
