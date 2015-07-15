package WebServer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.CompanyTable;
import DataBase.File;
import DataBase.HibernateTools;
import DataBase.LiuDongFuZ;
import DataBase.LiuDongZiC;
import DataBase.NliuDongFuZ;
import DataBase.NoLiuDongZc;
import DataBase.SuoYouZqy;
import DataBase.XianJinLl;
import DataBase.YinYeLiRun;
import DataBase.YinYeShouY;
import DataControl.FileDataControl;
import DataControl.TableDataControl;
import DataControl.TableItem;
import DataControl.TableTools;
import NetReptile.DataFormat.LogTool;
import ServerTools.ReturnDataTools;
import ServerTools.ServletTools;

/**
 * Servlet implementation class RedealTable
 */
@WebServlet("/RedealTable")
public class RedealTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RedealTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletTools.charSet(request, response);
		ReturnDataTools re=new ReturnDataTools();
		Map map=request.getParameterMap();
		HashMap<String,String> strMap=new HashMap<String, String>();
		/**
		 * 拿取表格数据
		 */
	    Set keSet=map.entrySet();
	    for(Iterator itr=keSet.iterator();itr.hasNext();){
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();  
	        Object ov=me.getValue();  
	        String[] value=new String[1];
	        if(ov instanceof String[]){  
	            value=(String[])ov;
	        }else{
	            value[0]=ov.toString();
	        }
	        for(int k=0;k<value.length;k++)
	        	strMap.put(((String)ok).replace("'", ""), value[k].replace(",", ""));
	      }
	    File file=FileDataControl.getControl().getFileById(Integer.valueOf(strMap.get("fileid")));
		CompanyTable table = TableDataControl.getControl().getTableByFile(file);
		int tab=Integer.valueOf(strMap.get("table"));
		switch (tab) {
		case TableTools.ZCFZ:
			LiuDongZiC ldzc = new LiuDongZiC();
			for (int i = 0; i < TableItem.LDZC.length; i++)
				setItemByClass(ldzc, strMap.get("" + (i + 1)), i + 1);
			HibernateTools.savaData(ldzc);
			table.setLiuDongZiC(ldzc);
			NoLiuDongZc nldzc = new NoLiuDongZc();
			for (int i = 0; i < TableItem.FLDZC.length; i++)
				setItemByClass(nldzc,
						strMap.get("" + (i + 1 + TableItem.LDZC.length)), i + 1);
			HibernateTools.savaData(nldzc);
			table.setNoLiuDongZc(nldzc);
			LiuDongFuZ ldfz = new LiuDongFuZ();
			for (int i = 0; i < TableItem.LDFZ.length; i++)
				setItemByClass(
						ldfz,
						strMap.get(""
								+ (i + 1 + TableItem.LDZC.length + TableItem.FLDZC.length)),
						i + 1);
			HibernateTools.savaData(ldfz);
			table.setLiuDongFuZ(ldfz);
			NliuDongFuZ nldfz = new NliuDongFuZ();
			for (int i = 0; i < TableItem.FLDFZ.length; i++)
				setItemByClass(
						nldfz,
						strMap.get(""
								+ (i + 1 + TableItem.LDZC.length
										+ TableItem.FLDZC.length + TableItem.LDFZ.length)),
						i + 1);
			HibernateTools.savaData(nldfz);
			table.setNliuDongFuZ(nldfz);
			SuoYouZqy syzqy = new SuoYouZqy();
			for (int i = 0; i < TableItem.SYZQY.length; i++)
				setItemByClass(
						syzqy,
						strMap.get(""
								+ (i + 1 + TableItem.LDZC.length
										+ TableItem.FLDZC.length
										+ TableItem.LDFZ.length + TableItem.FLDFZ.length)),
						i + 1);
			HibernateTools.savaData(syzqy);
			table.setSuoYouZqy(syzqy);
			break;
		case TableTools.LRB:
			YinYeShouY yysy=new YinYeShouY();
			for (int i = 0; i < TableItem.YinYLR.length; i++)
				setItemByClass(yysy, strMap.get("" + (i + 1)), i + 1);
			HibernateTools.savaData(yysy);
			table.setYinYeShouY(yysy);
			YinYeLiRun yylr=new YinYeLiRun();
			for (int i = 0; i < TableItem.YinYSY.length; i++)
				setItemByClass(yylr, strMap.get("" + (i + 1+TableItem.YinYLR.length)), i + 1);
			HibernateTools.savaData(yylr);
			table.setYinYeLiRun(yylr);
			break;
		case TableTools.XJLL:
			XianJinLl xjll=new XianJinLl();
			for (int i = 0; i < TableItem.XJLL.length; i++)
				setItemByClass(xjll, strMap.get("" + (i + 1)), i + 1);
			HibernateTools.savaData(xjll);
			table.setXianJinLl(xjll);
			break;
		case TableTools.SYZQYBD:
			break;
		default:
			break;
		}
		
		HibernateTools.updateData(table);
		file.setStatus(File.SUCCESS);
		HibernateTools.updateData(file);
		re.setSuccess(true);
		response.getWriter().write(re.toString());
	}
	/**
	 * 利用反射向对象中填充数据,并储存如数据库
	 * @param obj
	 * @param item
	 * @param i
	 * @return
	 */
	private boolean setItemByClass(Object obj, String item, int i) {
		Class cls = obj.getClass();
			try {
				Method method = cls.getDeclaredMethod("setItem" +i,String.class);
				method.invoke(obj, item);
			} catch (Exception e) {
				LogTool.E("error  in RedealTable ( setItemByClass )( setItem"+ i+"::" + e.toString());
				return false;
			}
		return true;
	}
}
