package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.MoneyModel;

public class MoneyParse {
    public static List<MoneyModel> getData (String html) throws Exception{
        //��ȡ�����ݣ�����ڼ�����
        List<MoneyModel> data = new ArrayList<MoneyModel>();
        //����Jsoup����
        Document doc = Jsoup.parse(html);
        //��ȡhtml��ǩ�е�����
        Elements elements=doc.select("tbody").select("tr");	
        for (Element ele:elements) {	
        	String ID=doc.select("h1[class=name]").select("span").select("a[href]").text();
        	String Year=ele.select("td:matchesOwn(^\\d{4}$)").text();
        	String Qian=ele.select("td:matchesOwn(^[+-]?\\d*\\.\\d*$)").text();
        	String aad=ele.select("td:matchesOwn(^\\d{1,2}$)").text();
        	String Songgu="0";
        	String Zhuangu="0";
        //	System.out.println("aa d:"+aad+"�ַ�������:"+aad.length());
        	if(aad.length()>1) {
        		String[] a=aad.split(" ");
        		 Songgu=a[0];
        		 Zhuangu=a[1];
        	}	
        	if(aad.length()==1)
        	{
        		Zhuangu=aad;
        	}

        	//String[] aa =aad.split(" ");
        	//String Songgu=aa[0];
           // String Zhuangu=aa[1];

            //����һ������������Կ�����ʹ��Model�����ƣ�ֱ�ӽ��з�װ
            MoneyModel moneyModel=new MoneyModel();
            //�����ֵ
            if(!(Year.equals(""))) {    //��Year��ֵΪ��ʱ ���������ݿ���
            moneyModel.setId(ID);
            moneyModel.setYear(Year);
            moneyModel.setQian(Qian);
            moneyModel.setSonggu(Songgu);
            moneyModel.setZhuangu(Zhuangu);

            //��ÿһ�������ֵ�����浽List������
            data.add(moneyModel);
            }
        }
        //��������
        return data;
    }
}