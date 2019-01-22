package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Serie;
import server.hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SerieService {
	
	private Session session = null;
	private Transaction transaction = null;
	
	public List<Serie> getAllSeriesPaginated(int start, int size) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Serie> arreglo = new ArrayList<>();
		for(Object oneObject : session.createQuery("FROM Serie a ORDER BY a.updateDate DESC")
				.getResultList()) {
			arreglo.add((Serie) oneObject);
		}
		session.close();
		return arreglo.subList(start, start+size);
	}
	
	public Serie addSerie(Serie serie) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(serie);
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
		return serie;
	}
	//Unique Serie by id
	public List<Serie> getSerie(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		List<Serie> serieHashSet = new ArrayList<>();
		/*Serie serie = session.find(Serie.class, id);
		serie.getEpisode().size();*/
		for(Object oneObject : session.createQuery("FROM Serie a where a.id=:id")
				.setParameter("id",id)
				.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")
				.getResultList()
		) {
			serieHashSet.add((Serie) oneObject);
		}
		transaction.commit();
		session.close();
		return serieHashSet;
	}
	/*"select distinct bd,sum(bpds.amount) from BillDetails as bd "
                    + "left join fetch bd.customerDetails as cd "
                    + "left join fetch bd.billProductList as bpd "
                    + "left join fetch bpd.product as pd "
                    +"left join fetch bd.billPaidDetailses as bpds "
                    + "where bd.billNo=:id "
                    + "and bd.client.id=:cid ";*/
	/*public Serie getSeries(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Serie anime =  session.get(Serie.class, id);
		transaction.commit();
		session.close();
		return anime;
	}*/
	/*Quien mas sabe mas disfruta*/
	public List<Serie> getAllSeries() {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Serie> arreglo = new ArrayList<>();
		for(Object oneObject : session.createQuery("FROM Serie a ORDER BY a.updateDate DESC")
				/*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*/  //para activar redis descomentar esto
				.setMaxResults(10)
				.getResultList()
		) {
			arreglo.add((Serie) oneObject);
		}
		session.close();
		return arreglo;
	}
	
	public Serie updateSerie(Serie serieid) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		session.update(serieid);
		transaction.commit();
		session.close();
		return serieid;
	}
	
	public void removeSerie(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Serie serie = session.get(Serie.class, id);
		session.delete(serie);
		transaction.commit();
		session.close();
	}
}
