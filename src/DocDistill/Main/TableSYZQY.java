package DocDistill.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import DataBase.CompanyTable;
import DataBase.File;
import DataControl.TableItem;
import DocDistill.DealInterface.TableDealAction;
import DocDistill.TableTools.TableRegx;
import DocDistill.TableTools.TableTools;

public class TableSYZQY implements TableDealAction{
	private static Pattern p[];
	private static String Item[];
	private static String mItem[];
	static {
		p = new Pattern[4];
		p[0] = Pattern.compile("^.{0,10}权益变动表.{0,20}$");
		p[1] = Pattern.compile("^.{0,10}上年年末余额.{0,20}$");
		p[3] = Pattern.compile("^.{0,10}所有者权益.{0,10}表.{0,20}$");
		p[2]=Pattern.compile("^[ :,　：]{0,50}半年度报告所采用的会计政策[ :,　：]{0,50}$");
		
		Item = new String[TableItem.SYouZQYMain.length+1];
		TableTools.StringArrayCopy(Item, TableRegx.SYouZQYMain, 0);
		Item[Item.length-1]="法定代表人";
		
		mItem = new String[TableItem.SYouZQYMain.length+1];
		TableTools.StringArrayCopy(mItem, TableItem.SYouZQYMain, 0);
		mItem[mItem.length-1]="法定代表人";
	}
	
	public TableSYZQY(){}
	/*表内容相关区域的String*/
	private  List<String> list = new ArrayList<String>();
	/*内容名与紧紧相接数据*/
	private  HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
	
	@Override
	public boolean isTable(String str) {
		for (int i = 0; i < p.length; i++)
			if (p[i].matcher(str).matches()){
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
		return true;
	}
	
	//判断是否可以加入List
	private void intoArrayList(ArrayList<String> list, String str) {
		if (!TableTools.isNumString(str))
			return;
		list.add(str);
	}
}
