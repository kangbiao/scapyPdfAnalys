package DataControl;

import java.util.HashMap;


public class TableItem {
	public static final String [] LDZC=new String[]{
		"货币资金",
		"金融资产",
		"应收票据",
		"应收账款",
		"预付款项",
		"应收利息",
		"应收股利",
		"其他应收款",
		"存货",
		"一年内到期的非流动资产",
		"其他流动资产",
		"流动资产合计"
	};
	public static final String [] FLDZC=new String[]{
		"可供出售金融资产",
		"持有至到期投资",
		"长期应收款",
		"长期股权投资",
		"投资性房地产",
		"固定资产",
		"在建工程",
		"工程物资",
		"固定资产清理",
		"生产性生物资产",
		"油气资产",
		"无形资产",
		"开发支出",
		"商誉",
		"长期待摊费用",
		"递延所得税资产",
		"其他非流动资产",
		"非流动资产合计"
	};
	public static final String [] LDFZ=new String[]{
		"短期借款",
		"金融负债",
		"应付票据",
		"应付账款",
		"预收款项",
		"应付职工薪酬",
		"应交税费",
		"应付利息",
		"应付股利",
		"其他应付款",
		"一年内到期的非流动负债",
		"其他流动负债",
		"流动负债合计"
	};
	
	public static final String [] FLDFZ=new String[]{
		"长期借款",
		"应付债券",
		"长期应付款",
		"专项应付款",
		"预计负债",
		"递延所得税负债",
		"其他非流动负债",
		"非流动负债合计"
	};
	
	public static final String [] SYZQY=new String[]{
		"股本",
		"资本公积",
		"库存股",
		"专项储备",
		"盈余公积",
		"未分配利润",
		"外币报表折算差额",
		"归属于母公司",
		"少数股东权益",
		"权益合计",
		"权益总计"
	};
	
	public static final String [] YinYSY=new String[]{
		"营业收入",
		"营业成本",
		"营业税金及附加",
		"销售费用",
		"管理费用",
		"财务费用",
		"资产减值损失",
		"公允价值变动收益",
		"投资收益",
		"对联营企业和合营",
		""
	};
	
	public static final String [] YinYLR=new String[]{
		"营业外收入",
		"",
		"营业外支出",
		"非流动资产处置损失",
		"利润总额",
		"所得税费用",
		"净利润",
		"归属于母公司",
		"少数股东损益",
		"每股收益",
		"基本每股收益",
		"稀释每股收益",
		"其他综合收益",
		"综合收益总额",
		"归属于母公司",
		"归属于少数股东"
	};
	
	public static final String [] XJLL=new String[]{
		"提供劳务收到的现金",
		"收到的税费返还",
		"收到其他与经营活动有关的现金",
		"经营活动现金流入小计",
		"接受劳务支付的现金",
		"支付给职工以及为职工支付的现金",
		"支付的各项税费",
		"支付其他与经营活动有关的现金",
		"经营活动现金流出小计",
		"经营活动产生的现金流量净额",
		"投资活动产生的现金流量",
		"收回投资收到的现金",
		"取得投资收益收到的现金",
		"无形资产和其他长期资产收回",
		"处置子公司及其他营业单位收到的现金净额",
		"收到其他与投资活动有关的现金",
		"投资活动现金流入小计",
		"无形资产和其他长期资产支付",
		"投资支付的现金",
		"取得子公司及其他营业单位支付的现金净额",
		"支付其他与投资活动有关的现金",
		"投资活动现金流出小计",
		"投资活动",
		"筹资活动",
		"汇率变动对现金及现金等价物的影响",
		"现金及现金等价物净增加额",
		"期初现金及现金等价物余额",
		"期末现金及现金等价物余额"
	};
	
	public static final String [] SYouZQYMain=new String[]{
		"加:会计政策变更",
		"前期差错更正",
		"其他",
		"本期增减变动金额",
		"综合收益总额",
		"股东投入和减少资本",
		"所有者投入资本",
		"股份支付计入所有者权益的金额",
		"其他",
		"提取盈余公积",
		"对所有者(或股东)的分配",
		"其他",
		"资本公积转增资本(或股本)",
		"盈余公积转增资本(或股本)",
		"盈余公积弥补亏损",
		"其他",
		"专项储备",
		"本期提取",
		"本期使用",
		"本期期末余额",
		"",
		"",
		"",
		"",
		""
	};
	
	public static final String[] TableName=new String[]{
		"",
		"",
		"",
		""
	};
	public static  HashMap<Integer, String[]> ItemMap=new HashMap<Integer, String[]>();
	public static  HashMap<Integer, String[]> ZCFZMap=new HashMap<Integer, String[]>();
	public static  HashMap<Integer, String[]> LRMap=new HashMap<Integer, String[]>();
	public static  HashMap<Integer, String[]> XJLLMap=new HashMap<Integer, String[]>();
	public static  HashMap<Integer, String[]> SYZQYMap=new HashMap<Integer, String[]>();
	static{
		ZCFZMap.put(1, LDZC);
		ZCFZMap.put(2, FLDZC);
		ZCFZMap.put(3, LDFZ);
		ZCFZMap.put(4, FLDFZ);
		ZCFZMap.put(5, SYZQY);
		LRMap.put(1, YinYSY);
		LRMap.put(2, YinYLR);
		XJLLMap.put(1, XJLL);
		SYZQYMap.put(1, SYouZQYMain);
		String mZCFZ[]=new String[]{
				"流动资产",
				"非流动资产",
				"流动负债",
				"非流动负债",
				"所有者权益(或股东权益)"
		};
		String mXJLL[]=new String[]{
				"现金流量"
		};
		String mLRB[]=new String[]{
				"营业总收入",
				"营业利润"
		};
		String mSYZQY[]=new String[]{
				"所有者权益"
		};
		ItemMap.put(TableTools.ZCFZ, mZCFZ);
		ItemMap.put(TableTools.LRB, mLRB);
		ItemMap.put(TableTools.XJLL, mXJLL);
//		ItemMap.put(TableTools.SYZQYBD, mSYZQY);
		
	}

}
