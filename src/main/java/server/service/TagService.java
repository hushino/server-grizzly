package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Serie;
import server.entity.Tag;
import server.hibernateUtil.HibernateUtil;

import java.util.List;

public class TagService {
	private Session session = null;
	private Transaction transaction = null;
	
	public List<Tag> getAllTagsOfSerie(Long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Serie serie = session.find(Serie.class, id);
		serie.getTags().size();
		List<Tag> tags = serie.getTags();
		session.close();
		return tags;
	}
	public List<Serie> getAll(Long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Tag tag = session.find(Tag.class, id);
		tag.getSeries().size();
		List<Serie> series = tag.getSeries();
		session.close();
		return series;
	}
	/*public List<Tag> getAllAnimeWhithTagName(String string) {
		session = HibernateUtil.getSessionFactory().openSession();
		List<Tag> Tag = new ArrayList<>();
		for(Object oneObject : session.createQuery(
				"Select o from Tag o where o.gender = :string")
				//Select o from Tag o where o.gender = :string
				//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId
				.setParameter("string",string)
				*//*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*//*
				.getResultList()
		) {
			Tag.add(( Tag ) oneObject);
		}
		session.close();
		return Tag;
	}*/
	
	
}
//from Tag a join fetch a.tags at join fetch at.tag where a.id =:id
/*session = HibernateUtil.getSessionFactory().openSession();
		List<Tag> Tag = new ArrayList<>();
		for(Object oneObject : session.createQuery(
		"Select o from Tag o where o.gender = :string")
		//Select o from Tag o where o.gender = :string
		//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId
		.setParameter("string",string)
				*//*.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")*//*
		.getResultList()
		) {
		Tag.add(( Tag ) oneObject);
		}
		session.close();
		return Tag;*/
