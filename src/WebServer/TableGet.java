package WebServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.CompanyTable;
import DataControl.TableDataControl;
import DataControl.TableTools;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;
import ServerTools.TableJsonTools;
/**
 * 获取表格数据
 * @author liaoshichao
 */
@WebServlet("/TableGet")
public class TableGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMPANYNAME="companyName";
	private static final String TABLETYPE="tableType";
	private static final String TABLEPART="tablePart";
	private static final String COLUMNS="tableColumns";
	private static final String YEAR="year";
	private static final String STATUS="status";
	
    public TableGet() {
        super(); 
    }
    
    public void outDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	doGet(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		TableJsonTools table=new TableJsonTools();//返回表格数据
		String companyName,year,columns;
		int status,tableType,tablePart;
		companyName=request.getParameter(COMPANYNAME);
		columns=request.getParameter(COLUMNS);
		tableType=Integer.valueOf(request.getParameter(TABLETYPE));
		tablePart=Integer.valueOf(request.getParameter(TABLEPART));
		year=request.getParameter(YEAR);
		status=Integer.valueOf(request.getParameter(STATUS));
		
		String name[]=dealCompanyArray(companyName.split(","));//公司名称
		//Json 数据List
		ArrayList<String> tablearr=TableTools.getTableHead(tableType,name);//表头
		ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>(); //数据
		//数据项表头
		data.add(TableTools.getTableColumnHead(tableType, name));
		
		//获取表格
		ArrayList<ArrayList<String>> temp=new ArrayList<ArrayList<String>>();
		for(int i=0;i<name.length;i++){
			CompanyTable mtable = TableDataControl.getControl()
					.getTableByFilteValue(name[i], year, status);
			if(mtable==null){
				temp.add(createNullList(tablearr.size()));
				continue;
			}
			 //获得表数据项目
			if (columns == null || columns.equals(""))
				temp.addAll(TableTools.getTableItem(mtable, tableType,
						tablePart, null));
			else
				temp.addAll(TableTools.getTableItem(mtable, tableType,
						tablePart, columns.split(",")));
		}
		//遍历temp 添加数据到data
		int dataNum = temp.size() / name.length;
		ArrayList<String> itemtemp=null;
		for(int i=0;i<dataNum;i++){
			itemtemp=new ArrayList<String>();
			itemtemp.add(temp.get(i).get(0));
			for(int index=0;index<name.length;index++){
				//1,2仅仅限于前三个表
				itemtemp.add(temp.get(i+dataNum*index).get(1));
				itemtemp.add(temp.get(i+dataNum*index).get(2));
			}
			data.add(itemtemp);
		}
		
		table.putTableHead(tablearr);
		table.putData(data);
		table.setSuccess(true);
		response.getWriter().write(table.toString());
		System.gc();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//创建空数据
	private ArrayList<String> createNullList(int n){
		ArrayList<String> item=new ArrayList<String>();
		for(int i=0;i<n-1;i++)
			item.add("无");
		return item;
	}
	
	/**
	 * 出于前端数据交换的需要
	 * @param name
	 * @return
	 */
	private String [] dealCompanyArray(String name[]){
		String temp[]=new String[name.length];
		for(int i=0;i<name.length;i++)
			temp[i]=name[i].split("-")[1];
		return temp;
	}
}
