package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.User;
import server.hibernateUtil.HibernateUtil;

public class UserService {
	private Session session = null;
	private Transaction transaction = null;
	
	public User getUser(String user) {
		session = HibernateUtil.getSessionFactory().openSession();
		User users =  session.get(User.class, user);
		users.getUser();
		session.close();
		return users;
	}
	
	public User addUser(User user) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(user);
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return user;
	}
	
	public boolean checkUser(String user ) {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			Object query = session.createQuery("FROM User a WHERE a.user = :username")
					.setParameter("username", user)
					//.setParameter("password", password)
					/*.setHint("org.hibernate.cacheable", true)
					.setCacheRegion("common")*/
					.uniqueResult();
			if ( query != null) {
				session.close();
				return true;
			}
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		session.close();
		return false;
	}
}
