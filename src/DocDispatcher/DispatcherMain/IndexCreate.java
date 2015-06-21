package DocDispatcher.DispatcherMain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DataBase.HibernateTools;
import DataControl.FileDataControl;
import ServerPublic.ServerPublic;
import ServerTools.FileReadTools;

/**
 * 为从Pdf提取而来的 Html创建目录
 * 提取多级目录并建立索引目录
 * 单例
 * @author liaoshichao
 */

public class IndexCreate {
	private static final String FrameReplace = "###";
	private static String LinkHref = "<li role=\"presentation\"><a href =\"index-html.html#@\" target =\"showframe\">"
			+ "$</a></li><br />";
	private static String LinkItem = "<a name=\"@\">";
	private static IndexCreate index;
	
	protected static IndexCreate getIndexCreate(){
		if(index==null)
			index=new IndexCreate();
		return index;
	}
	
	private IndexCreate() {
	}
	/*目录的正则表达式*/
	static Pattern p[]=new Pattern[6];
	static{
		p[0]=Pattern.compile("^\\d{1,}、.*$");                                        //" 数字、"
		p[1]=Pattern.compile("^[\u4E00-\u9FA5]、.*$");                       //"汉字、"
		p[2]=Pattern.compile("^\\(\\d{1,}\\)、.*$");                                // (数字)、
		p[3]=Pattern.compile("^\\([\u4E00-\u9FA5]{1,}\\)、.*$");         // (汉字)、
		p[4]=Pattern.compile("^\\(\\d{1,}\\).*$");
		p[5]=Pattern.compile("^\\([\u4E00-\u9FA5]{1,}\\).*$");
	}
	public static int isIndex(String str){
		for(int i=0;i<p.length;i++){
			if(p[i].matcher(str).matches())
				return i;
		}
		return -1;
	}
	
	/*创建目录*/
	protected void createIndex(String htmlpath){
		String folder=new File(htmlpath).getParent();
		String FrameFile=createFrameFile();

		LinkedList<TempLink> list=new LinkedList<TempLink>();
		File file=new File(htmlpath);
		Document doc=null;
		try{
		doc=Jsoup.parse(file, null);
		}catch(Exception e){
			return ;
		}
		Elements ptext=doc.select("p");
		int kind=0;
		int position=1;
		for (Element ele : ptext) {
			if(ele.text().length()>25||ele.text().length()<5) continue;
			if ((kind=isIndex(ele.text()))!=-1){
				ele.before(LinkItem.replace("@", "N"+position));
				list.add(new TempLink("N"+position++, ele.text()));
			}
		}
		savaFile(file, doc.html());  //保存修改
		
		/*创建索引*/
		StringBuilder sbuilder=new StringBuilder();
		for(TempLink link:list){
			String item=LinkHref.replace("@",link.at);
			item=item.replace("$", link.content);
			sbuilder.append(item);
		}
		FrameFile=FrameFile.replace(FrameReplace, sbuilder.toString());
		File mfile=new File(folder+File.separator+"frame.html");
		savaFile(mfile, FrameFile);
		File mainfile=new File(folder+File.separator+"main.html");
		File orgfile=new File(ServerPublic.CONFFOLD+File.separator+"main.html");
		savaFile(mainfile, FileReadTools.TextFileRead(orgfile));
		DataBase.File dbfile=FileDataControl.getControl().getFileByhtml(htmlpath.replace(ServerPublic.FolderPath, ""));
		if(dbfile==null)
			return ;
		  dbfile.setHtmlpath(mainfile.getAbsolutePath().replace(ServerPublic.FolderPath, ""));
		HibernateTools.updateData(dbfile);
		
		/* 通知表格提取进程提取表格数据 */
		TableDispathcher.getTableDispathcher().putFiledata(dbfile);
	}
	
	private String createFrameFile(){
		File file=new File(ServerPublic.CONFFOLD+File.separator+"frame.html");
		return FileReadTools.TextFileRead(file);
	}
	
	/* 储存文件 */
	private void savaFile(File file, String str) {
		try {
			FileOutputStream fos = new FileOutputStream(file, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(str);
			osw.close();
		} catch (Exception e) {
		}
	}
}

/*目录链接*/
class TempLink{
	String at;
	String content;
	public TempLink(String at,String content) {
		this.at=at;
		this.content=content;
	}
}
/*目录数据*/
class IndexData{
	int kind;
	int index;
	String content;
	protected IndexData(int kind,int index,String content){
		this.kind=kind;
		this.index=index;
		this.content=content;
	}
}
