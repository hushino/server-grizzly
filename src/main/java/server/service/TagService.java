package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Anime;
import server.entity.Episode;
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
				"from Anime a join fetch a.tags at join fetch at.tag where a.id =:id")
				//from Tag o join o.anime a where o.id =:id
				//select a.firstName, a.lastName from Book b join b.authors a where b.id = :id
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
	
	public List<Anime> getAll(Long id) {
		/*session = HibernateUtil.getSessionFactory().openSession();
		Anime anime = session.find(Anime.class, string);
		anime.getEpisode().size();
		List<Episode> episodes = anime.getEpisode();
		session.close();
		return episodes;*/
		session = HibernateUtil.getSessionFactory().openSession();
		Tag tag = session.find(Tag.class, id);
		tag.getAnime().size();
		List<Anime> anime = tag.getAnime();
		session.close();
		return anime;
	}
}

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
