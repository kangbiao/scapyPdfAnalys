package DocDistill.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;
import DataBase.LiuDongFuZ;
import DataBase.LiuDongZiC;
import DataBase.NliuDongFuZ;
import DataBase.NoLiuDongZc;
import DataBase.SuoYouZqy;
import DataControl.TableItem;
import DocDistill.DealInterface.TableDealAction;
import DocDistill.TableTools.TableRegx;
import DocDistill.TableTools.TableTools;

public class TableZCFZ implements TableDealAction{
	private static Pattern p[];
	private static String Item[];
	private static String mItem[];
	static {
		p = new Pattern[2];
		p[0] = Pattern.compile("^.{0,10}负[  ]{0,5}债[  ]{0,5}表[  ]{0,5}.{0,10}$");
		p[1]=Pattern.compile("^[ :,　：]{0,50}流动资产[ :,　：]{0,50}$");
		Item = new String[TableItem.LDZC.length + TableItem.FLDZC.length
				+ TableItem.LDFZ.length + TableItem.FLDFZ.length
				+ TableItem.SYZQY.length+1];
		TableTools.StringArrayCopy(Item, TableRegx.LDZC, 0);
		TableTools
				.StringArrayCopy(Item, TableRegx.FLDZC, TableRegx.LDZC.length);
		TableTools.StringArrayCopy(Item, TableRegx.LDFZ, TableRegx.LDZC.length
				+ TableRegx.FLDZC.length);
		TableTools.StringArrayCopy(Item, TableRegx.FLDFZ, TableRegx.LDZC.length
				+ TableRegx.FLDZC.length + TableRegx.LDFZ.length);
		TableTools.StringArrayCopy(Item, TableRegx.SYZQY, TableRegx.LDZC.length
				+ TableRegx.FLDZC.length + TableRegx.LDFZ.length
				+ TableRegx.FLDFZ.length);
		Item[Item.length-1]="法定代表人";
		
		mItem = new String[TableItem.LDZC.length + TableItem.FLDZC.length
		  				+ TableItem.LDFZ.length + TableItem.FLDFZ.length
		  				+ TableItem.SYZQY.length+1];
		  		TableTools.StringArrayCopy(mItem, TableItem.LDZC, 0);
		  		TableTools
		  				.StringArrayCopy(mItem, TableItem.FLDZC, TableItem.LDZC.length);
		  		TableTools.StringArrayCopy(mItem, TableItem.LDFZ, TableItem.LDZC.length
		  				+ TableItem.FLDZC.length);
		  		TableTools.StringArrayCopy(mItem, TableItem.FLDFZ, TableItem.LDZC.length
		  				+ TableItem.FLDZC.length + TableItem.LDFZ.length);
		  		TableTools.StringArrayCopy(mItem, TableItem.SYZQY, TableItem.LDZC.length
		  				+ TableItem.FLDZC.length + TableItem.LDFZ.length
		  				+ TableItem.FLDFZ.length);
		  		mItem[mItem.length-1]="法定代表人";
	}
	
	public TableZCFZ(){}
	/*表内容相关区域的String*/
	private  List<String> list = new ArrayList<String>();
	/*内容名与紧紧相接数据*/
	private  HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
	
	@Override
	public boolean isTable(String str) {
//		return false;
		String temp=str.replace(" ", "");
		if(temp.length()<15&&temp.contains("负债表"))
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
		LiuDongZiC ldzc=new LiuDongZiC();
		TableTools.appendStrToArrayofMap(value, TableItem.LDZC, map);
		TableTools.setItemByClass(ldzc, value);
		HibernateTools.savaData(ldzc);
		
		value=new ArrayList<String>();
		NoLiuDongZc nldzc=new NoLiuDongZc();
		TableTools.appendStrToArrayofMap(value, TableItem.FLDZC, map);
		TableTools.setItemByClass(nldzc, value);
		HibernateTools.savaData(nldzc);
		
		value=new ArrayList<String>();
		LiuDongFuZ ldfz=new LiuDongFuZ();
		TableTools.appendStrToArrayofMap(value, TableItem.LDFZ, map);
		TableTools.setItemByClass(ldfz, value);
		HibernateTools.savaData(ldfz);
		
		value=new ArrayList<String>();
		NliuDongFuZ nldfz=new NliuDongFuZ();
		TableTools.appendStrToArrayofMap(value, TableItem.FLDFZ, map);
		TableTools.setItemByClass(nldfz, value);
		HibernateTools.savaData(nldfz);
		
		value=new ArrayList<String>();
		SuoYouZqy syzqy=new SuoYouZqy();
		TableTools.appendStrToArrayofMap(value, TableItem.SYZQY, map);
		TableTools.setItemByClass(syzqy, value);
		HibernateTools.savaData(syzqy);
		
		table.setLiuDongZiC(ldzc);
		table.setNoLiuDongZc(nldzc);
		table.setLiuDongFuZ(ldfz);
		table.setNliuDongFuZ(nldfz);
		table.setSuoYouZqy(syzqy);
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
