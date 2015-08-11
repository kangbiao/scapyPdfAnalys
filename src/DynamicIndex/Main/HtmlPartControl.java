package DynamicIndex.Main;

import java.io.File;
import java.util.HashMap;

import DataControl.FileDataControl;
import NetReptile.DataFormat.LogTool;
import ServerPublic.ServerPublic;
import ServerTools.FileReadTools;

/**
 * 打开的含目录的文件池
 * 向Web端传递文件块
 * @author liaoshichao
 *
 */
public class HtmlPartControl {
	private static final int BUFFERSIZE = 10;
	private static HtmlPartControl control=null;
	private static final String REPLACE="######";
	/**
	 * 数据指针
	 * 数据指针与fileid 映射
	 */
	private static int point = 0;
	private static HashMap<Integer, Integer> pb=new HashMap<Integer, Integer>();
	/**
	 * 数据池
	 */
	private static HashMap<Integer, HashMap<Integer, String>> buffer = new HashMap<Integer, HashMap<Integer, String>>();
	public static HtmlPartControl getControl(){
		if(control==null)
			control=new HtmlPartControl();
		return control;
	}
	/**
	 * 获取与目录对应的html part
	 * @param fileid
	 * @param part
	 * @return
	 */
	public String getPartHtml(int fileid,int part){
		return createPartBody(getFromBuffer(fileid).get(part));
	}
	/**
	 * 从缓存区里取值
	 * @param fileid
	 * @param map
	 */
	public synchronized void putToBuffer(int fileid,HashMap<Integer, String> map){
		LogTool.I("HtmlPart put to buffer!");
		point = (point + 1) % BUFFERSIZE;
		if (pb.containsKey(point)) {
			buffer.remove(pb.get(point));
			pb.remove(point);
			pb.put(point, fileid);
			buffer.put(fileid, map);
			return;
		}
		pb.put(point, fileid);
		buffer.put(fileid, map);
	}
	
	/**
	 * 从buffer中获取map文件
	 * 若不存在则创建
	 * @param fileid
	 * @return
	 */
	private HashMap<Integer, String> getFromBuffer(int fileid){
		if(buffer.containsKey(fileid))
			return buffer.get(fileid);
		String htmlpath = ServerPublic.FolderPath
				+ FileDataControl.getControl().getFileById(fileid)
						.getHtmlpath();
		HashMap<Integer, String> map = IndexDeal.createIndexMap(htmlpath);
		putToBuffer(fileid, map);
		return map;
	}
	
	public String getHtmlFullPart(int fileid){
		String htmlpath = ServerPublic.FolderPath
				+ FileDataControl.getControl().getFileById(fileid)
						.getHtmlpath();
		return IndexDeal.createIndexHtml(htmlpath);
	}
	/**
	 * 创建part Body
	 * @param str
	 * @return
	 */
	private String createPartBody(String str) {
		String filebody = FileReadTools
				.TextFileRead(new File(ServerPublic.CONFFOLD + File.separator
						+ ServerPublic.HTMLMODE));
		return filebody.replace(REPLACE, str);
	}
}
