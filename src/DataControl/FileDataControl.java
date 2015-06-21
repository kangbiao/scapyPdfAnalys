package DataControl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DataBase.Company;
import DataBase.File;
import DataBase.HibernateTools;

/**
 * 文件数据管理
 * 单例
 * @author liaoshichao
 */
public class FileDataControl {
	private static FileDataControl control=null;
	private FileDataControl(){
	}
	
	public static FileDataControl getControl(){
		if(control==null)
			control=new FileDataControl();
		return control;
	}
	/**
	 * 通过PDF路径找到file
	 * @param path
	 * @return
	 */
	public File getFileByPath(String path){
		File file = null;
		String hql = "from File file where file.pdfpath= '" + path + "'";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<File> list = session.createQuery(hql).list();
		for (File f : list) {
			file=f;
		}
		HibernateTools.closeSession(session, tran);
		return file;
	}
	
	public File getFileByhtml(String path){
		File file = null;
		String hql = "from File file where file.htmlpath= '" + path + "'";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<File> list = session.createQuery(hql).list();
		for (File f : list) {
			file=f;
		}
		HibernateTools.closeSession(session, tran);
		return file;
	}
	
	public File getFileById(int id){
		File file = null;
		String hql = "from File file where file.id= "+id;
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<File> list = session.createQuery(hql).list();
		for (File f : list) {
			file=f;
		}
		HibernateTools.closeSession(session, tran);
		return file;
	}
	
	public int getFileNums(){
		String hql = "from File file ";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<File> list = session.createQuery(hql).list();
		HibernateTools.closeSession(session, tran);
		return list.size();
	}
	
	public int getUnDealFileNums(){
		String hql = "from File file where file.status ="+File.FAIL;
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<File> list = session.createQuery(hql).list();
		HibernateTools.closeSession(session, tran);
		return list.size();
	}
}
