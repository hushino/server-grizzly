package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Anime;
import server.entity.Tag;
import server.hibernateUtil.HibernateUtil;

import java.util.List;
import java.util.Set;

public class TagService {
	private Session session = null;
	private Transaction transaction = null;
	
	public Set<Tag> getAllTagsOfAnime(Long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Anime anime = session.find(Anime.class, id);
		anime.getTags().size();
		Set<Tag> tags = anime.getTags();
		session.close();
		return tags;
	}
	public List<Anime> getAll(Long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Tag tag = session.find(Tag.class, id);
		tag.getAnime().size();
		List<Anime> anime = tag.getAnime();
		session.close();
		return anime;
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
