package DocDispatcher.DispatcherMain;

import java.io.File;
import java.util.LinkedList;

import DataBase.HibernateTools;
import DataControl.FileDataControl;
import DocDispatcher.DataFormat.PdfFile;
import ServerPublic.ServerPublic;

/**
 * PDF文件解析 入口 先解析为Html,并提取目录 后调用NetDistill 提取表格内容
 * 单例
 * @author liaoshichao
 */
public class FileDispathcher {
	private static final String CMDPARAMS=" -q -s ";
	private static FileDispathcher dispathcher;
	private LinkedList<PdfFile> filelist; // 队列
	/* 文档处理状态 */
	private boolean isDeal=false;
	
	private FileDispathcher() {
	}
	
	public static FileDispathcher getFileDispathcher(){
		if(dispathcher==null)
			dispathcher=new FileDispathcher();
		return dispathcher;
	}
	
	/* 添加文件到队列 */
	public void putFiledata(String filepath, String filename,String folderpath) {
		if (filelist == null)
			filelist = new LinkedList<PdfFile>();
		synchronized (filelist) {
			filelist.add(new PdfFile(filepath, filename,folderpath));
		}
		/*通知处理*/
		NotifyToDeal();
	}

	/* 从队列中删除文件 */
	private PdfFile getFiledata() {
		if (filelist.size() <= 0)
			return null;
		synchronized (filelist) {
			return filelist.remove();	
		}
	}
	/* 通知进行文档处理 */
	private void NotifyToDeal(){
		if(isDeal)  return;
		new FileDispathThread().start();
	}
	
	/* 文档处理 */
	/* 转化为Html 文档并提取目录 */
	private class FileDispathThread extends Thread {
		@Override
		public void run() {
			try {
				pdfToHtml();
			} catch (Exception e) {
				isDeal = false;
			}
		}

		private void pdfToHtml() throws Exception {
			PdfFile pdf;
			while ((pdf = getFiledata()) != null) {
				isDeal = true;
				String htmlholder = pdf.getFolderpath() + File.separator
						+ ServerPublic.HTMLFOLD;
				File folder = new File(htmlholder);
				if (!folder.exists())
					folder.mkdirs();
				String htmlpath = htmlholder + File.separator
						+ ServerPublic.HTMLNAME;
				StringBuilder builder = new StringBuilder();
				builder.append("pdftohtml ").append(CMDPARAMS)
						.append(pdf.getFilepath()).append(" ")
						.append(htmlholder).append(ServerPublic.HTMLINDEX);
				try {
					Runtime runtime = Runtime.getRuntime();
					Process ps = runtime.exec(builder.toString());
					ps.waitFor();
					DataBase.File file = FileDataControl.getControl()
							.getFileByPath(
									pdf.getFilepath().replace(
											ServerPublic.FolderPath, ""));
					file.setHtmlpath(htmlpath.replace(ServerPublic.FolderPath,
							""));
					HibernateTools.updateData(file);
					/* 目录提取 */
					IndexCreate.getIndexCreate().createIndex(htmlpath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isDeal = false;
		}
	}
}
