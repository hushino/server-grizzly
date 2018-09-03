package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Anime;
import server.hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
	//Unique anime by id
	public Set<Anime> getAnime(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Set<Anime> animeHashSet = new LinkedHashSet<>();
		/*Anime anime = session.find(Anime.class, id);
		anime.getEpisode().size();*/
		for(Object oneObject : session.createQuery("FROM Anime as a   left join fetch a.episode as e where a.id=:id  and e.parentID=:episodeId")
				.setParameter("id",id)
				.setParameter("episodeId",id)
				/*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*/
				.getResultList()
		) {
			animeHashSet.add(( Anime ) oneObject);
		}
		transaction.commit();
		session.close();
		return animeHashSet;
	}
	/*"select distinct bd,sum(bpds.amount) from BillDetails as bd "
                    + "left join fetch bd.customerDetails as cd "
                    + "left join fetch bd.billProductList as bpd "
                    + "left join fetch bpd.product as pd "
                    +"left join fetch bd.billPaidDetailses as bpds "
                    + "where bd.billNo=:id "
                    + "and bd.client.id=:cid ";*/
	/*public Anime getAnime(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Anime anime =  session.get(Anime.class, id);
		transaction.commit();
		session.close();
		return anime;
	}*/
	
	public List<Anime> getAllAnimes() {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Anime> arreglo = new ArrayList<>();
		for(Object oneObject : session.createQuery("FROM Anime a ORDER BY a.updateDate DESC")
				.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")  //para activar redis descomentar esto
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
