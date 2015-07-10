package WebServer;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DataControl.TableItem;
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
	private static final String YEAR="year";
	private static final String STATUS="status";
    public LineCount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		String companyName,year;
		int status,tableType,tablePart;
		companyName=request.getParameter(COMPANYNAME);
		tableType=Integer.valueOf(request.getParameter(TABLETYPE));
		tablePart=Integer.valueOf(request.getParameter(TABLEPART));
		year=request.getParameter(YEAR);
		status=Integer.valueOf(request.getParameter(STATUS));
		String name[]=companyName.split(",");//公司名称
		
		HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> itemmap;
		for(int i=0;i<name.length;i++){
			if(name[0].equals("")) continue;
			itemmap=new HashMap<String, String>();
			String temp=System.currentTimeMillis()+"";
			itemmap.put(TableItem.ItemMap.get(tableType)[tablePart-1],temp.substring(temp.length() - 6, temp.length()));
			map.put(name[i], itemmap);
		}
		
		response.getWriter().write(new Gson().toJson(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
