package DataBase;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 加载Hibernate的配置文件
 *  数据处理的工具类
 * 单例
 * @author liaoshichao
 */
public class HibernateTools {
	private static SessionFactory factory;
	static {
		Configuration con=new Configuration().configure();
        ServiceRegistry registry=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        factory=con.buildSessionFactory(registry);
	}

	private HibernateTools() {
	}

	public static Session getSession() {
		return factory.openSession();
	}

	public static SessionFactory getFactory() {
		return factory;
	}

	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.clear();
			session.close();
		}
		return;
	}
	/**
	 * 关闭会话 和事务
	 * @param session
	 * @param tran
	 */
	public static void closeSession(Session session,Transaction tran) {
		if(tran!=null){
			tran.commit();
		}
		if (session != null && session.isOpen()) {
			session.clear();
			session.close();
		}
		return;
	}
	/**
	 * 更新数据 并完成持久化
	 * @param object
	 */
	public static void updateData(Object object){
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		session.update(object);
		closeSession(session,tran);
	}
	
	/**
	 * 从数据库中删除数据
	 * @param object
	 */
	public static void deleteData(Object object){
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		session.delete(object);
		closeSession(session,tran);
	}
	
	/**
	 * 数据持久化
	 * @param object
	 */
	public static void savaData(Object object){
		Session session=getSession();
		Transaction tran=session.beginTransaction();
		session.save(object);
		closeSession(session,tran);
	}
}
