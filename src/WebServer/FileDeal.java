package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import DataBase.File;
import DataBase.HibernateTools;
import DataControl.FileDataControl;
import DocDispatcher.DispatcherMain.TableDispathcher;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

/**
 * Servlet implementation class FileDeal
 */
@WebServlet("/FileDeal")
public class FileDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String fileid="fileidString";
	private static final String TYPE="type";
    public FileDeal() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		ReturnDataTools re=new ReturnDataTools();
		
		String id[]=request.getParameter(fileid).split(",");
		String type=request.getParameter(TYPE);
		for(int i=0;i<id.length;i++){
			if(type.equals("true"))
				dealFile(Integer.valueOf(id[i]));
			else  ignoFile(Integer.valueOf(id[i]));
		}
		re.setSuccess(true);
		response.getWriter().write(re.toString());
	}
	
	/* 处理 */
	private void dealFile(int id){
		File mfile=FileDataControl.getControl().getFileById(id);
		mfile.setStatus(File.SUCCESS);
		HibernateTools.updateData(mfile);
	}
	/* 忽略 */
	private void ignoFile(int id){
		File mfile=FileDataControl.getControl().getFileById(id);
		HibernateTools.deleteData(mfile);
	}
}
