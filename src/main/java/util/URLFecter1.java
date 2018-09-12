package util;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import model.MoneyModel;
import parse.MoneyParse;


public class URLFecter1 {
    public static List<MoneyModel> URLParser (HttpClient client, String url) throws Exception {
        //�������ս���������
        List<MoneyModel> moneyData = new ArrayList<MoneyModel>();
        //��ȡ��վ��Ӧ��html�����������HTTPUtils��
        HttpResponse response = HTTPUtils.getRawHtml(client, url);      
        //��ȡ��Ӧ״̬��
        int StatusCode = response.getStatusLine().getStatusCode();
        //���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");    
            moneyData = MoneyParse.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //�������ĵ�ʵ��
            EntityUtils.consume(response.getEntity());
        }
        return moneyData;
    }
}