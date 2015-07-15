package WebServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DataBase.CompanyTable;
import DataControl.TableDataControl;
import DataControl.TableItem;
import DataControl.TableTools;
import ServerTools.ServletTools;

/**
 * 柱状图
 * @author liaoshichao
 */
@WebServlet("/LineCount")
public class LineCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMPANYNAME="companyName";
	private static final String TABLETYPE="tableType";
	private static final String TABLEPART="tablePart";
	private static final String COLUMNS="tableColumns";
	private static final String YEAR="year";
	private static final String STATUS="status";
    public LineCount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		
		String companyName,year,columns;
		int status,tableType,tablePart;
		companyName=request.getParameter(COMPANYNAME);
		columns=request.getParameter(COLUMNS);
		tableType=Integer.valueOf(request.getParameter(TABLETYPE));
		tablePart=Integer.valueOf(request.getParameter(TABLEPART));
		year=request.getParameter(YEAR);
		status=Integer.valueOf(request.getParameter(STATUS));
		
		String name[]=companyName.split(",");//公司名称
		
		HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> itemmap;
		for(int i=0;i<name.length;i++){
			if(name[0].equals("")) continue;
			CompanyTable mtable = TableDataControl.getControl()
					.getTableByFilteValue(name[i], year, status);
			if(mtable==null)	continue;
			itemmap=new HashMap<String, String>();
			ArrayList<ArrayList> list=null;
			 //获得表数据项目
			if (columns == null || columns.equals(""))
				list = TableTools.getTableItem(mtable, tableType, tablePart,
						null);
			else
				list = TableTools.getTableItem(mtable, tableType, tablePart,
						columns.split(","));
			for(ArrayList<String> mlist:list){
				itemmap.put(mlist.get(1),mlist.get(2));
			}
			map.put(name[i], itemmap);
		}
		response.getWriter().write(new Gson().toJson(map));
		System.gc();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
