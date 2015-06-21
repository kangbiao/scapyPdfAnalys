package NetReptile.DataFormat;
/**
 * 接口信息
 * @author liaoshichao
 */
public class Internet {
	//主机地址
	public static final String HOST="http://www.neeq.cc";
	//获取编号信息接口
	public static final String DATAURL=HOST+"/controller/GetListCompany";
	//获取Pdf 详细信息
	public static final String PDFDATAURL=HOST+"/controller/GetDisclosureannouncementPage";
	//PDF 文件父地址
	public static final String PDFPARENT="http://file.neeq.com.cn/upload";
	//交易公开信息
	public static final String TRADE="http://www.neeq.cc/ajax/GetDisclosureannouncementTxT?files=2015-04-29&year=2015";
}
