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
		String companyName,year;
		int status,tableType,tablePart;
		companyName=request.getParameter(COMPANYNAME);
		tableType=Integer.valueOf(request.getParameter(TABLETYPE));
		tablePart=Integer.valueOf(request.getParameter(TABLEPART));
		year=request.getParameter(YEAR);
		status=Integer.valueOf(request.getParameter(STATUS));
		
		//Json 数据List
		ArrayList<String> tablearr=TableTools.getTableHead(tableType);//表头
		ArrayList<ArrayList> data=new ArrayList<ArrayList>(); //数据
		String name[]=companyName.split(",");//公司名称
		//获取表格
		for(int i=0;i<name.length;i++){
			CompanyTable mtable = TableDataControl.getControl()
					.getTableByFilteValue(name[i], year, status);
			if(mtable==null)
				data.add(createNullList(name[i],tablearr.size()));
			 //获得表数据项目
			data.addAll(TableTools.getTableItem(mtable, tableType, tablePart));
			data.add(createSplitList(tablearr.size()));
		}
		
		table.putTableHead(tablearr);
		table.putData(data);
		table.setSuccess(true);
		response.getWriter().write(table.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//创建空数据
	private ArrayList<String> createNullList(String name,int n){
		ArrayList<String> item=new ArrayList<String>();
		item.add(name);
		for(int i=0;i<n-1;i++){
			item.add("无");
		}
		return item;
	}
	
	//创建分割线
	private ArrayList<String> createSplitList(int n){
		ArrayList<String> item=new ArrayList<String>();
		for(int i=0;i<n;i++)
			item.add("----");
		return item;
	}
}
