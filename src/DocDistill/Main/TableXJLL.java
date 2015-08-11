package DocDistill.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;
import DataBase.XianJinLl;
import DataControl.TableItem;
import DocDistill.DealInterface.TableDealAction;
import DocDistill.TableTools.TableRegx;
import DocDistill.TableTools.TableTools;

public class TableXJLL implements TableDealAction{
	private static Pattern p[];
	private static String Item[];
	private static String mItem[];
	static {
		p = new Pattern[3];
		p[0] = Pattern.compile("^[ :,　：合并]{0,50}.{0,5}现金流量表[ :,　：]{0,50}$");
		p[1] = Pattern.compile("^[ :,　：]{0,50}经营活动产生的现金流量[ :,　：]{0,50}$");
		p[2] = Pattern.compile("^.{0,10}现[  ]{0,5}金[  ]{0,5}流[  ]{0,5}量[  ]{0,5}表[  ]{0,5}.{0,10}$");
		
		Item = new String[TableItem.XJLL.length+1];
		TableTools.StringArrayCopy(Item, TableRegx.XJLL, 0);
		Item[Item.length-1]="法定代表人";
		
		mItem = new String[TableItem.XJLL.length+1];
		TableTools.StringArrayCopy(mItem, TableItem.XJLL, 0);
		mItem[mItem.length-1]="法定代表人";
	}
	
	public TableXJLL(){}
	/*表内容相关区域的String*/
	private  List<String> list = new ArrayList<String>();
	/*内容名与紧紧相接数据*/
	private  HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
	
	@Override
	public boolean isTable(String str) {
//		return false;
		String temp=str.replace(" ", "");
		if(temp.length()<18&&temp.contains("现金流量表"))
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
		XianJinLl xjll=new XianJinLl();
		TableTools.appendStrToArrayofMap(value, TableItem.XJLL, map);
		TableTools.setItemByClass(xjll, value);
		HibernateTools.savaData(xjll);
		
		table.setXianJinLl(xjll);
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
