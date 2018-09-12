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
        //获取的数据，存放在集合中
        List<MoneyModel> data = new ArrayList<MoneyModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements=doc.select("tbody").select("tr");	
        for (Element ele:elements) {	
        	String ID=doc.select("h1[class=name]").select("span").select("a[href]").text();
        	String Year=ele.select("td:matchesOwn(^\\d{4}$)").text();
        	String Qian=ele.select("td:matchesOwn(^[+-]?\\d*\\.\\d*$)").text();
        	String aad=ele.select("td:matchesOwn(^\\d{1,2}$)").text();
        	String Songgu="0";
        	String Zhuangu="0";
        //	System.out.println("aa d:"+aad+"字符串长度:"+aad.length());
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

            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            MoneyModel moneyModel=new MoneyModel();
            //对象的值
            if(!(Year.equals(""))) {    //当Year的值为空时 不插入数据库中
            moneyModel.setId(ID);
            moneyModel.setYear(Year);
            moneyModel.setQian(Qian);
            moneyModel.setSonggu(Songgu);
            moneyModel.setZhuangu(Zhuangu);

            //将每一个对象的值，保存到List集合中
            data.add(moneyModel);
            }
        }
        //返回数据
        return data;
    }
}