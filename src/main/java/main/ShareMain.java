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
    //log4j��ʹ��
    static final Log logger = LogFactory.getLog(ShareMain.class);
    public static void main(String[] args) throws Exception {
        //��ʼ��һ��httpclient
        HttpClient client = new DefaultHttpClient();
        //����Ҫ��ȡ��һ����ַ��������Դ����ݿ��г�ȡ���ݣ�Ȼ������ѭ����������ȡһ��URL����
        String id="0600010";
        int m = Integer.parseInt( id );
        for(int i=1;i<30;i++) {
        	int s=m+1;
        	String a="0"+s;
        m++;
        String url="http://quotes.money.163.com/"+a+".html";
        System.out.println(url);
        //ץȡ������
        List<ShareModel> sharedatas=URLFecter.URLParser(client, url);
        //ѭ�����ץȡ������
       for (ShareModel share:sharedatas) {
            logger.info("ShareID:"+share.getShareID()+"\t"+"ShareName:"+share.getShareName());
      		}
       MYSQLControl.executeInsert(sharedatas);
        }
    }
        //��ץȡ�����ݲ������ݿ�
    
}