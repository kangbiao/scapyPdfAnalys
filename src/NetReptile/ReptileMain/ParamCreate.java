package NetReptile.ReptileMain;

/**
 *  本类用来构建 请求参数 
 * @author liaoshichao
 */
public class ParamCreate {
	public static final String ANNOUN_TYPE="1";  /* "公司公告"的Type 参数 */
	public static final String BUSINESS_TYPE="3";  /* "业务周知" */
	public static final String SUPERVISE_TYPE="6";  /* "监管信息" */
	public static final String CHECK_TYPE="7";  /* "审查信息" */
	
	private String type = "1";
	private String company_cd = "";
	private String key = null;
	private String subType = "0";
	private String startDate = null;
	private String endDate = null;
	private String queryParams = "0";
	private int page;
	
	public ParamCreate(String code,String type,String StartDate,String endDate){
		this.company_cd=code;
		this.type=type;
		this.startDate=StartDate;
		this.endDate=endDate;
	}
	
	public void setPage(int page){
		this.page=page;
	}
	
	/* 构建请求参数 */
	public static String createFDPParams(String url,ParamCreate pdf){
		StringBuilder builder=new StringBuilder();
		builder.append(url+"?");
		builder.append("type").append("=").append(pdf.type).append("&");
		builder.append("company_cd").append("=").append(pdf.company_cd).append("&");
		builder.append("key").append("=").append("&");
		builder.append("subType").append("=").append(pdf.subType).append("&");
		builder.append("startDate").append("=").append(pdf.startDate).append("&");
		builder.append("endDate").append("=").append(pdf.endDate).append("&");
		builder.append("queryParams").append("=").append(pdf.queryParams).append("&");
		builder.append("page").append("=").append(pdf.page);
		return builder.toString();
	}
}
