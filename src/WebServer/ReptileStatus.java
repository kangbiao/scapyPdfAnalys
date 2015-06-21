package WebServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NetReptile.ReptileMain.NetControl;
import ServerTools.ReturnDataTools;

/**
 * 获取状态
 * @author liaoshichao
 */
@WebServlet("/ReptileStatus")
public class ReptileStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReptileStatus() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnDataTools re=new ReturnDataTools();
		re.setSuccess(true);
		re.setJsonString(NetControl.getRunStatus());
		response.getWriter().write(re.toString());
		/**
		 * success true/false
		 * errorMessage 
		 * jsonString 
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
