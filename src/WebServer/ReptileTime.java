package WebServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

/**
 * Servlet implementation class ReptileTime
 */
@WebServlet("/ReptileTime")
public class ReptileTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String TIME="startTime";
    public ReptileTime() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String time=request.getParameter(TIME);
		ServletTools.charSet(request, response);
		ReturnDataTools re=new ReturnDataTools();
		String dt[]=time.split(":");
		try{
			
		}catch(Exception e){
			re.setSuccess(false);
			response.getWriter().write(re.toString());
			return;
		}
		re.setSuccess(true);
		response.getWriter().write(re.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
