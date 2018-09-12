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
    //log4j��ʹ��
    static final Log logger = LogFactory.getLog(MoneyMain.class);
    public static void main(String[] args) throws Exception {
        //��ʼ��һ��httpclient
        HttpClient client = new DefaultHttpClient();
        //����Ҫ��ȡ��һ����ַ��������Դ����ݿ��г�ȡ���ݣ�Ȼ������ѭ����������ȡһ��URL����
        String id="600010";
        int m = Integer.parseInt( id );
        for(int i=1;i<5;i++) {
        	int s=m+1;
        	String a=""+s;
        m++;
        String url="http://quotes.money.163.com/f10/fhpg_"+a+".html";
        System.out.println(url);
        //ץȡ������
        List<MoneyModel> moneydatas=URLFecter1.URLParser(client, url);
        //ѭ�����ץȡ������
       for (MoneyModel money:moneydatas) {
            logger.info("ID:"+money.getId()+"\t"+"year:"+money.getYear()+"\t"+"qian:"+money.getQian()
            +"\t"+"songgu:"+money.getSonggu()+"\t"+"zhuangu:"+money.getZhuangu());
      		}
       MYSQLControl.executeInsert1(moneydatas);
        }
    }
        //��ץȡ�����ݲ������ݿ�
    
}