import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import NetReptile.DataFormat.LogTool;

public class Test {
	public static void main(String[] args) {
		String htmlpath="/home/liaoshichao/Doctemp1/pic/index-html.html";
		File file = new File(htmlpath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, null);
		} catch (Exception e) {
			LogTool.E("error in IndexCreate(createIndexMap) Jsoup parse-->"+e.toString());
		}
		Elements eles=doc.select("p,img");
		System.out.println(eles.size());
		for(Element ele:eles){
			if(ele.nodeName().equals("p")){
				ele.attr("style",dealStyleStr(ele.attr("style")));
				System.out.println(ele.toString());
			}
		}	
	}
	
	private static String dealStyleStr(String str){
		String s[]=str.split(";");
		return s[0]+";"+s[2]+";"+s[3];
	}
}