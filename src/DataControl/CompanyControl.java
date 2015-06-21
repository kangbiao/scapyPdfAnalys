package DataControl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DataBase.Company;
import DataBase.HibernateTools;

/**
 * 公司数据控制类
 * 
 * @author liaoshichao
 */
public class CompanyControl {
	private static CompanyControl control = null;

	private CompanyControl() {
	}

	public static CompanyControl getControl() {
		if (control == null)
			control = new CompanyControl();
		return control;
	}

	/**
	 * 根据公司代码判断存在
	 * 
	 * @param code
	 * @return
	 */
	public boolean isExitByCompanyCode(String code) {
		boolean re = false;
		String hql = "from Company company where company.num= '" + code + "'";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<Company> list = session.createQuery(hql).list();
		if (list.size() > 0)
			re = true;
		HibernateTools.closeSession(session, tran);
		return re;
	}
	/**
	 * 获取公司数量
	 * @return
	 */
	public int getNumForCompany(){
		String hql = "from Company company";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<Company> list = session.createQuery(hql).list();
		HibernateTools.closeSession(session, tran);
		return list.size();
	}
	
	/**
	 * 获取公司
	 * @param code
	 * @return
	 */
	public Company getCompanyByCode(String code) {
		Company company = null;
		String hql = "from Company company where company.num= '" + code + "'";
		Session session = HibernateTools.getSession();
		Transaction tran = session.beginTransaction();
		List<Company> list = session.createQuery(hql).list();
		for (Company cpy : list) {
			company = cpy;
		}
		HibernateTools.closeSession(session, tran);
		return company;
	}
}
