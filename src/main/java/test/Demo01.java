package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo01 {

	public static void main(String[] args) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault(); // ����httpclientʵ��
        HttpGet httpget = new HttpGet("http://quotes.money.163.com/f10/fhpg_600010.html"); // ����httpgetʵ��
         
        CloseableHttpResponse response = httpclient.execute(httpget); // ִ��get����
        HttpEntity entity=response.getEntity(); // ��ȡ����ʵ��
        String content=EntityUtils.toString(entity, "utf-8");
        response.close(); // �ر������ͷ�ϵͳ��Դ
        
        Document doc=Jsoup.parse(content); // ������ҳ �õ��ĵ�����
        Elements elements=doc.getElementsByTag("tbody").select("tr").select("td"); // ��ȡtag��title������DOMԪ��
        Element element=elements.get(0); // ��ȡ��1��Ԫ��
        String title=element.text(); // ����Ԫ�ص��ı�
        System.out.println("��ҳ�����ǣ�"+title);
      //  Elements elements=doc.select("tbody").select("tr");	
        
//        for (Element ele:elements) {	
//        	String ID=doc.select("h1[class=name]").select("span").select("a[href]").text();
//        	String Year=ele.select("td:matchesOwn(^\\d{4}$)").text();
//        	String Qian=ele.select("td:matchesOwn(^[+-]?\\d*\\.\\d*$)").text();
//        	//String aad=ele.select("td:matchesOwn(^\\d{1,2}$)").text();
//        	System.out.println("id:"+ID+"  Year:"+Year+"  Qian:"+Qian);
//         
//	}
}
}