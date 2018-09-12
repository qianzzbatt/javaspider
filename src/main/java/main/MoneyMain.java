package main;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import db.MYSQLControl;
import model.MoneyModel;
import util.URLFecter1;

public class MoneyMain {
    //log4j的使用
    static final Log logger = LogFactory.getLog(MoneyMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String id="600010";
        int m = Integer.parseInt( id );
        for(int i=1;i<5;i++) {
        	int s=m+1;
        	String a=""+s;
        m++;
        String url="http://quotes.money.163.com/f10/fhpg_"+a+".html";
        System.out.println(url);
        //抓取的数据
        List<MoneyModel> moneydatas=URLFecter1.URLParser(client, url);
        //循环输出抓取的数据
       for (MoneyModel money:moneydatas) {
            logger.info("ID:"+money.getId()+"\t"+"year:"+money.getYear()+"\t"+"qian:"+money.getQian()
            +"\t"+"songgu:"+money.getSonggu()+"\t"+"zhuangu:"+money.getZhuangu());
      		}
       MYSQLControl.executeInsert1(moneydatas);
        }
    }
        //将抓取的数据插入数据库
    
}