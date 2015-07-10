package WebServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DataControl.TableTools;
import ServerTools.ServletTools;
/**
 * 饼状图数据返回
 * @author liaoshichao
 */
@WebServlet("/PieCount")
public class PieCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMPANY="companyName";
    private static final String TABLETYPR="tableType";
    public PieCount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		String name=request.getParameter(COMPANY);
		int type=Integer.valueOf(request.getParameter(TABLETYPR));
		JSONObject json=new JSONObject();
		switch(type){
		case TableTools.ZCFZ:
			json.put("现金流量", "6985632.15");
			json.put("利润总计", "4344252.15");
			json.put("流动负债", "644632.15");
			break;
		case TableTools.LRB:
			json.put("管理费用", "234632.15");
			json.put("经营活动", "6425632.15");
			break;
		case TableTools.XJLL:
			json.put("投资活动", "7432632.15");
			json.put("利润分配", "5325632.15");
			break;
		case TableTools.SYZQYBD:
			break;
			default: break;
		}
		response.getWriter().write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
