package server.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Episode;
import server.hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeService {
	private Session session = null;
	private Transaction transaction = null;
	
	public List<Episode> getAllEpisodesWithLimit() {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> episodeArrayList = new ArrayList<>();
		for(Object object : session.createQuery("FROM Episode a ORDER BY a.updateDate DESC")
				.setMaxResults(3)
				.getResultList()
		)
		{
			episodeArrayList.add((Episode) object);
		}
		session.close();
		return episodeArrayList;
	}
}
