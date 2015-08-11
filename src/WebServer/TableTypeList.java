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

import DataControl.TableItem;
import DataControl.TableTools;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

@WebServlet("/TableTypeList")
public class TableTypeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String COMPANY="companyName";
    private static final String TABLETYPR="tableType";
    private static final String TABLEPART="tablePart";
    public TableTypeList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		ReturnDataTools re=new ReturnDataTools();
		
		String name=request.getParameter(COMPANY);
		int type=Integer.valueOf(request.getParameter(TABLETYPR));
		String part=null;
		try {
			part = request.getParameter(TABLEPART);
		} catch (Exception e) {
			part = null;
		}
		
		String listname[]=TableItem.ItemMap.get(type);
		ArrayList<ItemClass> list=new ArrayList<TableTypeList.ItemClass>();
		if (part == null) {
			for (int i = 0; i < listname.length; i++) 
				list.add(new ItemClass(i + 1 + "", listname[i]));
		}else{
			int mpart=Integer.valueOf(part);
			HashMap<Integer, String[]> map=null;
			switch (type) {
			case TableTools.ZCFZ:
				map=TableItem.ZCFZMap;
				break;
			case TableTools.LRB:
				map=TableItem.LRMap;
				break;
			case TableTools.XJLL:
				map=TableItem.XJLLMap;
				break;
			case TableTools.SYZQYBD:
				map=TableItem.SYZQYMap;
				break;
			default:
				re.setSuccess(false);
				response.getWriter().write(re.toString());
				return;
			}
			String itemlist[]=map.get(mpart);
			for(int i=0;i<itemlist.length;i++)
				list.add(new ItemClass((i+1)+"", itemlist[i]));
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
