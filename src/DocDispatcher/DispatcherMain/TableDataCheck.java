package DocDispatcher.DispatcherMain;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;

public class TableDataCheck {
	public static void startCheck(File file,CompanyTable table,boolean dealErro){
		boolean isSuccess=true;
		file.setStatus(File.SUCCESS);
		if(table.getXianJinLl().getItem27().length()<5)
			isSuccess=false;
		if(table.getLiuDongZiC().getItem1().length()<5)
			isSuccess=false;
		
		
		if(!isSuccess||dealErro)
			file.setStatus(File.FAIL);
		HibernateTools.updateData(file);
	}
}
