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
        //��ȡ�����ݣ�����ڼ�����
        List<ShareModel> data = new ArrayList<ShareModel>();
        //����Jsoup����
        Document doc = Jsoup.parse(html);
        //��ȡhtml��ǩ�е�����
      //  Elements elements=doc.select("ul[id=Irecentstock]").select("li[class=clearfix]");	
       // for (Element ele:elements) {	
        	String shareName=doc.select("h1[class=name]").select("a").first().text();
            String shareID=doc.select("h1[class=name]").select("span").select("a[href]").text();

            //����һ������������Կ�����ʹ��Model�����ƣ�ֱ�ӽ��з�װ
            ShareModel shareModel=new ShareModel();
            //�����ֵ
            shareModel.setShareID(shareID);
            shareModel.setShareName(shareName);

            //��ÿһ�������ֵ�����浽List������
            data.add(shareModel);
      //  }
        //��������
        return data;
    }
}