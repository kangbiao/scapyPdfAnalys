package NetReptile.DataFormat;
/**
 * 获取PDF文件的JSON数据信息
 * @author liaoshichao
 */
public class PDFData {
	// 数据的节点 JsonArray 没有数据时为null
	public static final String JSONDATA = "disclosureInfos";
	// 文件url
	public static final String FILEPATH = "filePath";
	// 公司编号
	public static final String COMPANYCODE = "companyCode";
	// 公司名字
	public static final String COMPANYNAME = "companyName";
	// 文件名
	public static final String FILENAME = "titleFull";
	// 发布时间
	public static final String UPTIME = "uploadDateTime";
	
	private String FileUrl;
	private String FileName;
	private String Time;

	public PDFData(String url, String name, String time) {
		this.FileUrl = url;
		this.FileName = name;
		this.Time = time;
	}

	public String getFileUrl() {
		return this.FileUrl;
	}

	public String getFileName() {
		return this.FileName;
	}

	public String getTime() {
		return this.Time;
	}
}
