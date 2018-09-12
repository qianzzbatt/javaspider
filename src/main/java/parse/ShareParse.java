package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.ShareModel;

public class ShareParse {
    public static List<ShareModel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<ShareModel> data = new ArrayList<ShareModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
      //  Elements elements=doc.select("ul[id=Irecentstock]").select("li[class=clearfix]");	
       // for (Element ele:elements) {	
        	String shareName=doc.select("h1[class=name]").select("a").first().text();
            String shareID=doc.select("h1[class=name]").select("span").select("a[href]").text();

            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            ShareModel shareModel=new ShareModel();
            //对象的值
            shareModel.setShareID(shareID);
            shareModel.setShareName(shareName);

            //将每一个对象的值，保存到List集合中
            data.add(shareModel);
      //  }
        //返回数据
        return data;
    }
}