package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Anime;
import server.hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AnimeService {
	
	private Session session = null;
	private Transaction transaction = null;
	
	public List<Anime> getAllAnimesPaginated(int start, int size) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Anime> arreglo = new ArrayList<>();
		for(Object oneObject : session.createQuery("FROM Anime a ORDER BY a.updateDate DESC")
				.getResultList()) {
			arreglo.add(( Anime ) oneObject);
		}
		session.close();
		return arreglo.subList(start, start+size);
	}
	
	public Anime addAnime(Anime anime) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(anime);
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
		return anime;
	}
	
	public Anime getAnime(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Anime anime =  session.get(Anime.class, id);
		transaction.commit();
		session.close();
		return anime;
	}
	
	public List<Anime> getAllAnimes() {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Anime> arreglo = new ArrayList<>();
		for(Object oneObject : session.createQuery("FROM Anime a ORDER BY a.updateDate DESC")
				.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")
				.setMaxResults(10)
				.getResultList()
		) {
			arreglo.add(( Anime ) oneObject);
		}
		session.close();
		return arreglo;
	}
	
	public Anime updateAnime(Anime animeid) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		session.update(animeid);
		transaction.commit();
		session.close();
		return animeid;
	}
	
	public void removeAnime(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Anime anime = session.get(Anime.class, id);
		session.delete(anime);
		transaction.commit();
		session.close();
	}
}
