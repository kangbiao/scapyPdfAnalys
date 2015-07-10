package DataControl;

import java.util.List;

import org.hibernate.Session;

import DataBase.CompanyTable;
import DataBase.HibernateTools;

public class TableDataControl {
	private static TableDataControl control;
	private TableDataControl(){
	}
	
	public static TableDataControl getControl(){
		if(control==null)
			control=new TableDataControl();
		return control;
	}
	/**
	 * 通过筛选条件获得表格
	 * @param name 名字
	 * @param year 年
	 * @param status 状态 1上半年 2下半年
	 * @return
	 */
	public CompanyTable getTableByFilteValue(String name,String year,int status){
		CompanyTable table=null;
		String hql = "from CompanyTable table where table.company.name= '"
				+ name + "'" + " and table.year= '" + year
				+ "' and table.status=" + status;
		Session session=HibernateTools.getSession();
		List<CompanyTable> list=session.createQuery(hql).list();
		for(CompanyTable t:list){
			table=t;
		}
		return table;
	}
}
