package DocDispatcher.DispatcherMain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;
import DataControl.CompanyControl;
import DataControl.FileDataControl;
import DocDistill.DealInterface.TableDealAction;
import DocDistill.Main.TableLR;
import DocDistill.Main.TableSYZQY;
import DocDistill.Main.TableXJLL;
import DocDistill.Main.TableZCFZ;
import NetReptile.DataFormat.LogTool;
import ServerPublic.ServerPublic;
/**
 * 提取数据库数据入库
 * 单例
 * @author liaoshichao
 */
public class TableDispathcher {
	private static TableDispathcher dispathcher;
	private LinkedList<File> filelist; // 队列
	
	/* 文档处理状态 */
	private boolean isDeal=false;
	private TableDispathcher(){
	}
	
	public static TableDispathcher getTableDispathcher(){
		if(dispathcher==null)
			dispathcher=new TableDispathcher();
		return dispathcher;
	}
	
	/* 添加文件到队列 */
	public void putFiledata(File file) {
		if (filelist == null)
			filelist = new LinkedList<File>();
		synchronized (filelist) {
			filelist.add(file);
		}
		/*通知处理*/
		NotifyToDeal();
	}

	/* 从队列中删除文件 */
	private File getFiledata() {
		if (filelist.size() <= 0)
			return null;
		synchronized (filelist) {
			return filelist.remove();
		}
	}
	/* 通知进行文档处理 */
	private void NotifyToDeal(){
		if(isDeal)  return;
		new TableThread().start();
	}
	
	/**
	 * 文档数据处理
	 * 转化并提取数据
	 */
	class TableThread extends Thread{
		@Override
		public void run() {
			try {
				getTableData();
			} catch (Exception e) {
				isDeal = false;
			}
		}
		private void getTableData() throws Exception{
			File file;
			while((file=getFiledata())!=null){
				isDeal = true;
				boolean dealError=false;
				/*非年度报告*/
				if (!(p1.matcher(file.getFilename()).matches() || p2.matcher(
						file.getFilename()).matches())
						|| p3.matcher(file.getFilename()).matches()
						|| file.getFilename().contains("通知")) {
					file.setStatus(File.SUCCESS);
					HibernateTools.updateData(file);
					continue;
				}
				
				String htmlpath = ServerPublic.FolderPath + file.getHtmlpath();
				htmlpath=htmlpath.replace("main.html", ServerPublic.HTMLNAME);
				System.out.println(htmlpath);
				java.io.File htmlfile = new java.io.File(htmlpath);
				Document doc = null;
				TableFilter fliter=new TableFilter();
				try {
					doc = Jsoup.parse(htmlfile, null);
				} catch (Exception e) {
					LogTool.E("in TableDispathcher :\n"+e.toString());
					dealError=true;
				}
				Elements eles = doc.select("p");
				int index = 0;
				TableDealAction deal = fliter.dealList.get(index++);
				TableDealAction predeal = null;
				boolean temp = false;
				for (Element ele : eles) {
					if (deal.isTable(ele.text())) {
						temp = true;
						if (index >= fliter.dealList.size())
							break;
						predeal = deal;
						deal = fliter.dealList.get(index++);
					}
						
					if (temp) {
						predeal.addtoList(ele.text());
					}
				}
			/**
			 * 创建表格数据
			 */
				CompanyTable ctable=new CompanyTable();
				ctable.setCompany(CompanyControl.getControl().getCompanyByFile(file));
				ctable.setStatus(p2.matcher(file.getFilename()).matches() ? CompanyTable.FYEAR
						: CompanyTable.EYEAR);
				ctable.setYear(getYearFromStr(file.getFilename()));
				ctable.setFile(file);
				HibernateTools.savaData(ctable);
				/**
				 * 依次处理
				 * 并判断是否处理失败
				 * 传入 Database.File  CompanyTable
				 */
				for(TableDealAction dealtable:fliter.dealList){
					if(!dealtable.doDealDoc(file,ctable))
						dealError=true;
				}
				
				/**
				 * 文档处理完毕
				 * 检查勾稽关系
				 */
				TableDataCheck.startCheck(file, ctable, dealError);
			}
			isDeal=false;
		}
	}
	
	private static String ndbg="^.*年度报告.*$";
	private static String bndbg="^.*半年度报告.*$";
	private static String ndbgzy="^.*年度报告摘要.*$";
	private static Pattern p1=Pattern.compile(ndbg);
	private static Pattern p2=Pattern.compile(bndbg);
	private static Pattern p3=Pattern.compile(ndbgzy);
	/**
	 * 从文件名中获取年份
	 * @param str 文件名
	 * @return
	 */
	private static String getYearFromStr(String str){
		StringBuilder temp=new StringBuilder();
		for(int i=0,n=0;i<str.length();i++){
			if(n>=4) break;
			if(str.charAt(i)>=48 && str.charAt(i)<=57){
				n++;
				temp.append(str.charAt(i));
			}
		}
		return temp.toString();
	}
	/**
	 * 筛选表格的类
	 * @author liaoshichao
	 */
	class TableFilter {
		List<TableDealAction> dealList = new ArrayList<TableDealAction>();

		public TableFilter() {
			dealList.add(new TableZCFZ());
			dealList.add(new TableLR());
			dealList.add(new TableXJLL());
			dealList.add(new TableSYZQY());
		}
	}
	
	public static void main(String args[]){
//		File file=new File();
//		file.setFilename("中科软2013半年度报告");
//		file.setHtmlpath("/430002中科软/[定期报告]中科软:2014年年度报告/html/main.html");
		//"/430002中科软/[定期报告]中科软:2014年年度报告/html/main.html"
//		String filepath="/430002中科软/[定期报告]中科软:2014年年度报告/html/main.html";
		String filepath="/430003北京时代/[定期报告]北京时代:2014年年度报告/html/main.html";
		File file=FileDataControl.getControl().getFileByhtml(filepath);
		TableDispathcher.getTableDispathcher().putFiledata(file);
	}
}
