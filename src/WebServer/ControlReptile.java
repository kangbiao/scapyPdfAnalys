package WebServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NetReptile.ReptileMain.NetControl;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

/**
 * 对爬虫的控制
 * @author liaoshichao
 */
@WebServlet("/ControlReptile")
public class ControlReptile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATUS="status";
	private static final String STOP="stop";
	private static final String START="start";
    public ControlReptile() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		ReturnDataTools re=new ReturnDataTools();
		String status=null;
		try{
			status=request.getParameter(STATUS);
		}catch(Exception e){
			status=null;
		}
		if(status==null){
			re.setSuccess(false);
			response.getWriter().write(re.toString());
			return;
		}
		switch(status){
		case START:
			NetControl.StartReptile();
			re.setSuccess(true);
			break;
		case STOP:
			NetControl.CloseReptile();
			re.setSuccess(true);
			break;
			default: break;
		}
		response.getWriter().write(re.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
