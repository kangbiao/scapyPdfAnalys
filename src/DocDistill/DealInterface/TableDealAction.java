package DocDistill.DealInterface;

import DataBase.CompanyTable;
import DataBase.File;

/**
 * 数据处理类实现的接口
 * 
 * @author liaoshichao
 * 
 */
public interface TableDealAction {
	boolean isTable(String str);

	void addtoList(String str);

	boolean doDealDoc(File file, CompanyTable table);

	void initList();
}
