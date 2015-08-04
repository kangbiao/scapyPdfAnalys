package DynamicIndex.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import NetReptile.DataFormat.LogTool;
import ServerPublic.ServerPublic;
import ServerTools.FileReadTools;

public class IndexDeal {
	/**
	 * 一 二 三 四 级目录
	 */
	public static final int ONEINDEX=1;
	public static final int TWOINDEX=2;
	public static final int THREEINDEX=3;
	public static final int FOURINDEX=4;
	
	private static final String REPLACE="######";
	private static String framepath="index-frame.html";
	private static String itemhtml="<li partid=\"@\"><a href=\"javascript:void(0)\" >######</a>";
	private static final String itemLi="</li>";
	private static final String ItemUL="<ul >";
	private static final String TwoItemLi="\n </ul> \n</li>\n";
	/**
	 * 创建文档四级目录
	 * 并返回模版文档
	 * @param htmlpath
	 * @return
	 */
	public static String createIndexHtml(String htmlpath){
		File file = new File(htmlpath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, null);
		} catch (Exception e) {
			LogTool.E("error in IndexCreate Jsoup parse-->"+e.toString());
			return null;
		}
		/**
		 * 分析文件产生目录
		 */
		int kind=0;
		ArrayList<Item> link=new ArrayList<Item>();
		Elements ptext = doc.select("p");
		for (Element ele : ptext) {
			String eltext=ele.text().replace(" ", "");
			if ((kind=isIndex(ele.text()))!=-1){
				if(eltext.contains("第一节"))
					link.clear();
				link.add(new Item(eltext,kind));
			}
		}
		ArrayList<Item> array =new ArrayList<Item>();
		int preitem=0, preweight=0;
		int num=-1,firstindex=0;
		StringBuilder builde=new StringBuilder();
		for (Item item : link) {
			num++;
			if (preitem == 0 || item.weight == ONEINDEX
					|| item.weight == firstindex || item.weight < firstindex) {
				array.add(new Item(item.text,ONEINDEX));
				if(preitem!=0)
					createForUL(preweight-ONEINDEX, builde);
				builde.append("\n")
						.append(itemhtml.replace(REPLACE, item.text).replace("@",num+""));
				preitem=item.weight;
				firstindex=preitem;
				preweight=ONEINDEX;
				continue;
			}
			if(item.weight == preitem){
				builde.append(itemLi).append("\n")
				    .append(itemhtml.replace(REPLACE,item.text).replace("@",num+""));
				array.add(new Item(item.text,preweight));
				preitem=item.weight;
				continue;
			}
			
			if(item.weight > preitem){
				preweight++;
				builde.append("\n").append(ItemUL).append("\n")
						.append(itemhtml.replace(REPLACE, item.text).replace("@",num+""));
				array.add(new Item(item.text,preweight));
				preitem=item.weight;
				continue;
			}
			
			if(item.weight < preitem){
				if (preweight - item.weight < 0) {
					builde.append("\n").append(ItemUL).append("\n")
							.append(itemhtml.replace(REPLACE, item.text).replace("@",num+""));
				} else {
					createForUL(preweight - item.weight, builde);
					builde.append("\n").append(
							itemhtml.replace(REPLACE, item.text).replace("@",num+""));
				}
				preweight=item.weight;
				array.add(new Item(item.text,preweight));
				preitem=item.weight;
				continue;
			}
		}
		
		if (array.size() > 0) {
			/* 填补Index剩余的东西 */
			if (array.get(array.size() - 1).weight == ONEINDEX)
				builde.append(itemLi);
			else {
				for (int i = 0; i < array.get(array.size() - 1).weight; i++)
					createForUL(array.get(array.size() - 1).weight, builde);
			}
		}
		
		/**
		 * 构造目录html文件
		 */
//		String indexfile=getIndexMode();
//        return indexfile.replace(REPLACE, builde.toString());
		return builde.toString();
	}
	/**
	 * 创建目录映射map;
	 * @param htmlpath
	 * @return
	 */
	public static HashMap<Integer, String> createIndexMap(String htmlpath){
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		/*文档解析*/
		File file = new File(htmlpath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, null);
		} catch (Exception e) {
			LogTool.E("error in IndexCreate(createIndexMap) Jsoup parse-->"+e.toString());
			return null;
		}
		/* 获取文档结构 */
		int kind=0,position=0;
		ArrayList<PositionItem> list=new ArrayList<PositionItem>();
		ArrayList<Item> strlist=new ArrayList<Item>();
		Elements ptext = doc.select("p,img");
		for (Element ele : ptext) {
			String eltext=ele.text().replace(" ", "");
			/*替换图片为相对路径*/
			if(ele.nodeName().equals("img")){
				String str=ele.attr("src");
				String strvalue = htmlpath.replace(file.getName(), str)
						.replace(ServerPublic.ServerPath + File.separator, "");
				ele.attr("src", strvalue);
			}
			/*去掉top标签*/
			if(ele.nodeName().equals("p"))
				ele.attr("style",dealStyleStr(ele.attr("style")));
			if ((kind=isIndex(ele.text()))!=-1){
				strlist.add(new Item(changeAbsToRela(ele),kind));
				if(eltext.contains("第一节"))
					list.clear();
				list.add(new PositionItem(position, kind));
				position++;
				continue;
			}
			strlist.add(new Item(changeAbsToRela(ele),-1));
			position++;
		}
		
		ArrayList<PositionItem> array =new ArrayList<PositionItem>();
		int preitem=0, preweight=0;
		int firstindex=0;
		for (PositionItem item:list) {
			if (preitem == 0 || item.weight == ONEINDEX
					|| item.weight == firstindex || item.weight < firstindex) {
				array.add(new PositionItem(item.position,ONEINDEX));
				preitem=item.weight;
				firstindex=preitem;
				preweight=ONEINDEX;
				continue;
			}
			if(item.weight == preitem){
				array.add(new PositionItem(item.position,preweight));
				preitem=item.weight;
				continue;
			}
			
			if(item.weight > preitem){
				preweight++;
				array.add(new PositionItem(item.position,preweight));
				preitem=item.weight;
				continue;
			}
			
			if(item.weight < preitem){
				preweight=item.weight;
				array.add(new PositionItem(item.position,preweight));
				preitem=item.weight;
				continue;
			}
		}
		/**
		 * 产生map文件
		 */
		for(int i=0;i<array.size();i++){
			map.put(i, getMapItem(array.get(i).position, getPostion(i,array.get(i).weight,array), strlist));
		}
		return map;
	}
	/*Tools*/
	/**
	 * 获取某目录在文档中的位置
	 * @param index
	 * @param weight
	 * @param list
	 * @return
	 */
	private static int getPostion(int index,int weight,ArrayList<PositionItem> list){
		int re=list.get(index).position;
		for(int i=index+1;i<list.size();i++){
			re=list.get(i).position;
			if(list.get(i).weight<=weight) break;
		}
		return re;
	}
	/**
	 * 去掉文档中的 top参数
	 * @param str
	 * @return
	 */
	private static String dealStyleStr(String str){
		String s[]=str.split(";");
		return s[0]+";"+s[2]+";"+s[3];
	}
	/**
	 * 将绝对路径替换为相对路径
	 * @param ele
	 * @return
	 */
	private static String changeAbsToRela(Element ele){
		String attr=ele.attr("style").replace("absolute", "relative");
		ele.attr("style", attr);
		return ele.toString();
	}
	/**
	 * 遍历获取两个节点之间的数据
	 */
	private static String getMapItem(int first,int end,ArrayList<Item> strlist){
		StringBuilder builder=new StringBuilder();
		for(int i=first;i<end;i++){
			builder.append("\n").append(strlist.get(i).text);
		}
		return builder.toString();
	}
	
	public static void main(String args[]){
//		String html="/home/liaoshichao/index-html.html";
//		HashMap<Integer, String> map=IndexDeal.createIndexMap(html);
//		System.out.println(map.get(1));
	}
	/**
	 * 获取原模版文件
	 * @return
	 */
	private static String getIndexMode(){
		File file=new File(ServerPublic.CONFFOLD+File.separator+ServerPublic.INDEXMODE);
		return FileReadTools.TextFileRead(file);
	}
	/**
	 * 创建<ul> <li>
	 * @param n
	 * @param builder
	 */
	private static void createForUL(int n ,StringBuilder builder){
		if(n<=0) return;
		builder.append(itemLi);
		for(int i=0;i<n;i++)
			builder.append(TwoItemLi);
	}
	private static int isIndex(String str) {
		for (int i = 0; i < oneIndex.length; i++) {
			if (oneIndex[i].matcher(str).matches())
				return ONEINDEX;
		}
		for (int i = 0; i < twoIndex.length; i++) {
			if (twoIndex[i].matcher(str).matches())
				return TWOINDEX;
		}
		for (int i = 0; i < threeIndex.length; i++) {
			if (threeIndex[i].matcher(str).matches())
				return THREEINDEX;
		}
		for (int i = 0; i < fourIndex.length; i++) {
			if (fourIndex[i].matcher(str).matches())
				return FOURINDEX;
		}
		return -1;
	}

	private static int isOneIndex(String str) {
		for (int i = 0; i < oneIndex.length; i++) {
			if (oneIndex[i].matcher(str).matches())
				return ONEINDEX;
		}
		return -1;
	}

	private static int isTwoIndex(String str) {
		for (int i = 0; i < twoIndex.length; i++) {
			if (twoIndex[i].matcher(str).matches())
				return TWOINDEX;
		}
		return -1;
	}

	private static int isThreeIndex(String str) {
		for (int i = 0; i < threeIndex.length; i++) {
			if (threeIndex[i].matcher(str).matches())
				return THREEINDEX;
		}
		return -1;
	}

	private static int isFourIndex(String str) {
		for (int i = 0; i < fourIndex.length; i++) {
			if (fourIndex[i].matcher(str).matches())
				return FOURINDEX;
		}
		return -1;
	}
	
	static Pattern oneIndex[]=new Pattern[1];
	static Pattern twoIndex[]=new Pattern[1];
	static Pattern threeIndex[]=new Pattern[1];
	static Pattern fourIndex[]=new Pattern[1];
	static{
		//一级目录
		oneIndex[0] = Pattern.compile("^[ ]{0,20}第.{1,2}节[ ]{0,4}.{0,35}[ ]{0,20}$");
		//二级目录
		twoIndex[0] = Pattern.compile("^[ ]{0,20}[一二三四五六七八九十]{1,3}、[ ]{0,2}.{0,30}[ ]{0,20}$");
		//三级目录
		threeIndex[0] = Pattern.compile("^[ ]{0,20}[(（][一二三四五六七八九十]{1,3}[)）][ ]{0,2}.{0,35}[ ]{0,20}$");
		//四级目录
		fourIndex[0] = Pattern.compile("^[ ]{0,20}[0-9]{1,2}、[ ]{0,2}.{0,35}[ ]{0,20}$");
	}
}
/**
 * 代表子目录条目
 * @author liaoshichao
 */
class Item{
	public Item(String text,int weight){
		this.text=text;
		this.weight=weight;
	}
	protected String text;
	protected int weight;
}
/**
 * 记录子目录权重与位置
 *@author liaoshichao
 */
class PositionItem{
	public PositionItem(int position,int weight){
		this.position=position;
		this.weight=weight;
	}
	protected int position;
	protected int weight;
}
