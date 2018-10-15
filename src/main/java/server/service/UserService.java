package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.User;
import server.hibernateUtil.HibernateUtil;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

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
	
	public Boolean checkUser(String user) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		try {
			Object query = session.createQuery("FROM User a WHERE a.user = :user")
					.setParameter("user", user)
					//.setParameter("password", password)
					/*.setHint("org.hibernate.cacheable", true)
					.setCacheRegion("common")*/
					.getResultList();
			if ( query != null) {
                session.close();
                /*NewCookie cookie1 = new NewCookie("silogeado", "otracoki");
                Response.ResponseBuilder rb = Response.ok("mycoki");
                Response response = rb.cookie(cookie1)
                        .build();
                return response;*/
              return true;
			}
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		session.close();
        /*NewCookie cookie2 = new NewCookie("Nologeado", "1");
        Response.ResponseBuilder rbb = Response.ok("no" + "browser");
        Response response = rbb.cookie(cookie2)
                .build();*/
        return false;
	}
}
