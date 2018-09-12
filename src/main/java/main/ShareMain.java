package main;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import db.MYSQLControl;
import model.ShareModel;
import util.URLFecter;

public class ShareMain {
    //log4j的使用
    static final Log logger = LogFactory.getLog(ShareMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String id="0600010";
        int m = Integer.parseInt( id );
        for(int i=1;i<30;i++) {
        	int s=m+1;
        	String a="0"+s;
        m++;
        String url="http://quotes.money.163.com/"+a+".html";
        System.out.println(url);
        //抓取的数据
        List<ShareModel> sharedatas=URLFecter.URLParser(client, url);
        //循环输出抓取的数据
       for (ShareModel share:sharedatas) {
            logger.info("ShareID:"+share.getShareID()+"\t"+"ShareName:"+share.getShareName());
      		}
       MYSQLControl.executeInsert(sharedatas);
        }
    }
        //将抓取的数据插入数据库
    
}