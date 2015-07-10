package WebServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DataControl.TableItem;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

@WebServlet("/TableTypeList")
public class TableTypeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String COMPANY="companyName";
    private static final String TABLETYPR="tableType";
    public TableTypeList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		String name=request.getParameter(COMPANY);
		ReturnDataTools re=new ReturnDataTools();
		
		System.out.println(request.getParameter(TABLETYPR));
		int type=Integer.valueOf(request.getParameter(TABLETYPR));
		String listname[]=TableItem.ItemMap.get(type);
		ArrayList<ItemClass> list=new ArrayList<TableTypeList.ItemClass>();
		
		for(int i=0;i<listname.length;i++){
			list.add(new ItemClass(i+1+"",listname[i]));
		}
		re.setSuccess(true);
		re.setJsonString(list);
		response.getWriter().write(re.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private class ItemClass{
		String id;
		String name;
		public ItemClass(String id,String name) {
			this.id=id;
			this.name=name;
		}
	}
}
