package DocDistill.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;
import DataBase.YinYeLiRun;
import DataBase.YinYeShouY;
import DataControl.TableItem;
import DocDistill.DealInterface.TableDealAction;
import DocDistill.TableTools.TableRegx;
import DocDistill.TableTools.TableTools;

public class TableLR implements TableDealAction{
	private static Pattern p[];
	private static String Item[];
	private static String mItem[];
	static {
		p = new Pattern[3];
		p[0] = Pattern.compile("^[ :,　：合并]{0,50}.{0,5}利润表[ :,　：]{0,50}$");
		p[1]=Pattern.compile("^.{0,10}利润分配表.{0,20}$");
		p[2]=Pattern.compile("^.{0,10}利.{0,2}润.{0,2}表.{0,10}$");
		
		Item = new String[TableItem.YinYLR.length+TableItem.YinYSY.length+1];
		TableTools.StringArrayCopy(Item, TableRegx.YinYSY, 0);
		TableTools.StringArrayCopy(Item, TableRegx.YinYLR, TableRegx.YinYSY.length);
		Item[Item.length-1]="法定代表人";
		
		mItem = new String[TableItem.YinYLR.length+TableItem.YinYSY.length+1];
		TableTools.StringArrayCopy(mItem, TableItem.YinYSY, 0);
		TableTools.StringArrayCopy(mItem, TableItem.YinYLR, TableItem.YinYSY.length);
		mItem[mItem.length-1]="法定代表人";
	}
	public TableLR(){}
	/*表内容相关区域的String*/
	private  List<String> list = new ArrayList<String>();
	/*内容名与紧紧相接数据*/
	private  HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
	
	@Override
	public boolean isTable(String str) {
//		return false;
		String temp=str.replace(" ", "");
		if(temp.length()<18&&temp.contains("利润表"))
			return true;
		for (int i = 0; i < p.length; i++)
			if (p[i].matcher(str).matches()) {
				return true;
			}
		return false;
	}

	public void initList(){
		map.clear();
		list.clear();
	}
	@Override
	public void addtoList(String str) {
		this.list.add(str);
	}

	@Override
	public boolean doDealDoc(File file,CompanyTable table) {
		boolean isAdd=false;
		String name=null;
		ArrayList<String> mlist=new ArrayList<String>();
		for (int i = 0, n = 0; i < list.size(); i++) {
			if (TableTools.RegexbyStr(Item[n], list.get(i))) {
				if (isAdd) {
					map.put(name, mlist);
					mlist = new ArrayList<String>();
				}
				isAdd = true;
				name = mItem[n++];
				if (n >= Item.length)
					n--;
			}
			if (isAdd) {
				intoArrayList(mlist, list.get(i));
			}
		}
		ArrayList<String> value=new ArrayList<String>();
		YinYeShouY yysy=new YinYeShouY();
		TableTools.appendStrToArrayofMap(value, TableItem.YinYSY, map);
		TableTools.setItemByClass(yysy, value);
		HibernateTools.savaData(yysy);
		
		YinYeLiRun lirun=new YinYeLiRun();
		TableTools.appendStrToArrayofMap(value, TableItem.YinYLR, map);
		TableTools.setItemByClass(lirun, value);
		HibernateTools.savaData(lirun);
		
		table.setYinYeShouY(yysy);
		table.setYinYeLiRun(lirun);
		HibernateTools.updateData(table);
		return true;
	}
	
	//判断是否可以加入List
	private void intoArrayList(ArrayList<String> list, String str) {
		if (!TableTools.isNumString(str))
			return;
		list.add(str);
	}
}
