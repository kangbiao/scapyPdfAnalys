package DataControl;

import java.lang.reflect.Method;
import java.util.ArrayList;

import DataBase.CompanyTable;
import DataBase.LiuDongFuZ;
import DataBase.LiuDongZiC;
import DataBase.NliuDongFuZ;
import DataBase.NoLiuDongZc;
import DataBase.SuoYouZqy;
import DataBase.XianJinLl;
import DataBase.YinYeLiRun;
import DataBase.YinYeShouY;
import NetReptile.DataFormat.LogTool;
/**
 * 创造表数据
 * @author liaoshichao
 *
 */
public class TableTools {
	public static final int ZCFZ = 1; // 资产负载表
	public static final int LRB = 2; // 利润表
	public static final int XJLL = 3; // 现金流量表
	public static final int SYZQYBD = 4; // 所有者权益变动
	
	/**
	 * 创建表头
	 * @param i
	 * @return
	 */
	public static ArrayList<String> getTableHead(int i) {
		ArrayList<String> item = new ArrayList<String>();
		switch(i){
		case ZCFZ:
			item.add("公司名称");
			item.add("项目");
			item.add("期末余额");
			item.add("年初余额");
			break;
		case LRB:
			item.add("公司名称");
			item.add("项目");
			item.add("期末余额");
			item.add("年初余额");
			break;
		case XJLL:
			item.add("公司名称");
			item.add("项目");
			item.add("期末余额");
			item.add("年初余额");
			break;
		case SYZQYBD:
			item.add("公司名称");
			item.add("项目");
			item.add("实收资本");
			item.add("资本公积");
			item.add("减:库存股");
			item.add("其他综合收益");
			item.add("专项储备");
			item.add("盈余公积");
			item.add("未分配利润");
			break;
			default : break;
		}
		return item;
	}
	
	/**
	 * 获取完整的第三级表
	 * @param tab CompanyTable
	 * @param tabletype 表格type
	 * @param tablepart 表格part
	 * @return
	 */
	public static ArrayList<ArrayList> getTableItem(CompanyTable tab,int tabletype, int tablepart,String column[]) {
		ArrayList<ArrayList> item = null;
		switch (tabletype) {
		case ZCFZ:
			try {
				item = getZCFZItem(tab, tablepart,column);
			} catch (Exception e) {
				LogTool.E("error in TableTools getTableItem:ZCFZ--->" + e.toString());
			}
			break;
		case LRB:
			try {
				item = getLRBItem(tab, tablepart,column);
			} catch (Exception e) {
				LogTool.E("error in TableTools getTableItem:LRB--->" + e.toString());
			}
			break;
		case XJLL:
			try {
				item = getXJLLItem(tab, tablepart,column);
			} catch (Exception e) {
				LogTool.E("error in TableTools getTableItem:XJLL--->" + e.toString());
			}
			break;
		case SYZQYBD:
			try {
				item = getSYZQYBDItem(tab, tablepart,column);
			} catch (Exception e) {
				LogTool.E("error in TableTools getTableItem--->" + e.toString());
			}
			break;
		default:
			break;
		}
		return item;
	}
	/**
	 * create Column 数组便于利用反射获取数据
	 * @param length
	 * @return
	 */
	private static String [] createColumn(int length){
		String column[]=new String[length];
		for(int i=1;i<length+1;i++)
			column[i-1]=i+"";
		return column;
	}
	
	private static ArrayList<ArrayList> getZCFZItem(CompanyTable tab,int tablepart,String column[]) throws Exception{
		ArrayList<ArrayList> item=new ArrayList<ArrayList>();
		ArrayList<String> oiten;
		int i=0;
		switch(tablepart){
		case 0:
			oiten=new ArrayList<String>();
			break;
		case 1:
			if(column==null)
				column=createColumn(TableItem.LDZC.length);
			LiuDongZiC ldzc=tab.getLiuDongZiC();
			Class clsldzc=ldzc.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.LDZC[Integer.valueOf(column[n])-1]);
				Method method=clsldzc.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(ldzc));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			// 12
			break;
		case 2:
			if(column==null)
				column=createColumn(TableItem.FLDZC.length);
			NoLiuDongZc nldzc=tab.getNoLiuDongZc();
			Class clsnldzc=nldzc.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.FLDZC[Integer.valueOf(column[n])-1]);
				Method method=clsnldzc.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(nldzc));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			//  18
			break;
		case 3:
			if(column==null)
				column=createColumn(TableItem.LDFZ.length);
			LiuDongFuZ ldfz=tab.getLiuDongFuZ();
			Class clsldfz=ldfz.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.LDFZ[Integer.valueOf(column[n])-1]);
				Method method=clsldfz.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(ldfz));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			//  13
			break;
		case 4:
			if(column==null)
				column=createColumn(TableItem.FLDFZ.length);
			NliuDongFuZ fldfz=tab.getNliuDongFuZ();
			Class clsfldfz=fldfz.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.FLDFZ[Integer.valueOf(column[n])-1]);
				Method method=clsfldfz.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(fldfz));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			// 8
			break;
		case 5:
			if(column==null)
				column=createColumn(TableItem.SYZQY.length);
			SuoYouZqy syzqy=tab.getSuoYouZqy();
			Class clssyzqy=syzqy.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.SYZQY[Integer.valueOf(column[n])-1]);
				Method method=clssyzqy.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(syzqy));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			//  11
			break;
			default: break;
		}
		return item;
	}
	private static ArrayList<ArrayList> getLRBItem(CompanyTable tab,int tablepart,String column[]) throws Exception{
		ArrayList<ArrayList> item=new ArrayList<ArrayList>();
		ArrayList<String> oiten;
		int i=0;
		switch(tablepart){
		case 0:
			i=0;
			break;
		case 1:
			if(column==null)
				column=createColumn(TableItem.YinYSY.length);
			YinYeShouY yysy=tab.getYinYeShouY();
			Class clsyysy=yysy.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.YinYSY[Integer.valueOf(column[n])-1]);
				Method method=clsyysy.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(yysy));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			// 11
			break;
		case 2:
			if(column==null)
				column=createColumn(TableItem.YinYLR.length);
			YinYeLiRun yylr=tab.getYinYeLiRun();
			Class clsyylr=yylr.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.YinYLR[Integer.valueOf(column[n])-1]);
				Method method=clsyylr.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(yylr));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			//16
			break;
			default: break;
		}
		return item;
	}
	private static ArrayList<ArrayList> getXJLLItem(CompanyTable tab,int tablepart,String column[]) throws Exception{
		ArrayList<ArrayList> item=new ArrayList<ArrayList>();
		ArrayList<String> oiten;
		switch(tablepart){
		case 1:
			if(column==null)
				column=createColumn(TableItem.XJLL.length);
			XianJinLl xjll=tab.getXianJinLl();
			Class clsxjll=xjll.getClass();
			for(int n=0;n<column.length;n++){
				oiten=new ArrayList<String>();
				oiten.add(tab.getCompany().getName());
				oiten.add(TableItem.XJLL[Integer.valueOf(column[n])-1]);
				Method method=clsxjll.getDeclaredMethod("getItem"+column[n]);
				String temp=(String)(method.invoke(xjll));
				String t1="",t2="";
				try{
					t1=temp.split(",")[0];
					t2=temp.split(",")[1];
				}catch(Exception e){
					t1=t2="-";
				}
				oiten.add(t1);
				oiten.add(t2);
				item.add(oiten);
			}
			//28
			break;
			default: break;
		}
		return item;
	}
	
	private static ArrayList<ArrayList> getSYZQYBDItem(CompanyTable tab,int tablepart,String column[]){
		ArrayList<ArrayList> item=new ArrayList<ArrayList>();
		switch(tablepart){
		case 0:
			break;
			default: break;
		}
		return item;
	}
}
