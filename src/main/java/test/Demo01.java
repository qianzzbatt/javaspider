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
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpget = new HttpGet("http://quotes.money.163.com/f10/fhpg_600010.html"); // 创建httpget实例
         
        CloseableHttpResponse response = httpclient.execute(httpget); // 执行get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        String content=EntityUtils.toString(entity, "utf-8");
        response.close(); // 关闭流和释放系统资源
        
        Document doc=Jsoup.parse(content); // 解析网页 得到文档对象
        Elements elements=doc.getElementsByTag("tbody").select("tr").select("td"); // 获取tag是title的所有DOM元素
        Element element=elements.get(0); // 获取第1个元素
        String title=element.text(); // 返回元素的文本
        System.out.println("网页标题是："+title);
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