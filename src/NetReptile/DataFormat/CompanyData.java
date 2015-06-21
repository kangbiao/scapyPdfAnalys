package NetReptile.DataFormat;

public class CompanyData {
	// 根节点名称 String
	public static final String JsonRoot = "remoteData";
	// 数据的节点 JsonArray
	public static final String JsonData = "codetable";
	// 证券代码
	public static final String ZQCode = "ZQDM";
	// 证劵简称(公司名称)
	public static final String CompanyName = "ZQJC";
	// 转让类型
	public static final String Kind = "ZRLXMC";
	// 所属行业
	public static final String Trade = "HYZL";

	private String code;
	private String name;
	private String trade;
	private int position;  /* companydata 的序号 */

	public CompanyData(String code, String name, String trade) {
		this.code = code;
		this.name = name;
		this.trade = trade;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public String getTrade() {
		return this.trade;
	}

	public CompanyData setPosition(int p) {
		this.position = p;
		return this;
	}

	public int getPosition() {
		return this.position;
	}
}
