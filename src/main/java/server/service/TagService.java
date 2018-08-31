package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Tag;
import server.hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TagService {
	private Session session = null;
	private Transaction transaction = null;
	
	public List<Tag> getAllTagsOfAnime(Long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		List<Tag> Tag = new ArrayList<>();
		for(Object oneObject : session.createQuery(
				"Select o from Tag o where o.anime.id = :id ORDER BY o.updateDate DESC")
				//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId
				.setParameter("id",id)
				/*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*/
				.getResultList()
		) {
			Tag.add(( Tag ) oneObject);
		}
		session.close();
		return Tag;
	}
	
	public List<Tag> getAllAnimeWhithTagName(String string) {
		session = HibernateUtil.getSessionFactory().openSession();
		List<Tag> Tag = new ArrayList<>();
		for(Object oneObject : session.createQuery(
				"Select o from Tag o where o.gender = :string")
				//Select o from Tag o where o.gender = :string
				//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId
				.setParameter("string",string)
				/*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*/
				.getResultList()
		) {
			Tag.add(( Tag ) oneObject);
		}
		session.close();
		return Tag;
	}
}
