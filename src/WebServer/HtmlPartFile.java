package WebServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataControl.FileDataControl;
import DynamicIndex.Main.HtmlPartControl;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

/**
 * 动态生成文件目录
 * @author liaoshichao
 */
@WebServlet("/HtmlPartFile")
public class HtmlPartFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FILEID="fileid";
	private static final String PARTID="partid";
	
    public HtmlPartFile() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		int id = Integer.valueOf(request.getParameter(FILEID));
		int part = Integer.valueOf(request.getParameter(PARTID));
		response.getWriter().write(
				HtmlPartControl.getControl().getPartHtml(id, part));
	}

}
