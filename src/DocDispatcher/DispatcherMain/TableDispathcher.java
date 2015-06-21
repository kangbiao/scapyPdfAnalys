package DocDispatcher.DispatcherMain;

import java.util.LinkedList;
import java.util.regex.Pattern;

import DataBase.File;
import DataBase.HibernateTools;
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
		getTableData();
	}
	
	//数据处理
	private void getTableData(){
		File file;
		while((file=getFiledata())!=null){
			isDeal=true;
			if(p1.matcher(file.getFilename()).matches()||p2.matcher(file.getFilename()).matches()){
			if(System.currentTimeMillis()%3==0)
				file.setStatus(File.FAIL);
			else file.setStatus(File.SUCCESS);
			HibernateTools.updateData(file);
			}else{
				file.setStatus(File.SUCCESS);
				HibernateTools.updateData(file);
			}
		}
		isDeal=false;
	}
	
	public static String ndbg="^.*年度报告.*$";
	public static String bndbg="^.*半年度报告.*$";
	public static Pattern p1=Pattern.compile(ndbg);
	public static Pattern p2=Pattern.compile(bndbg);
}
