package server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.entity.Anime;
import server.entity.Episode;
import server.hibernateUtil.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class EpisodeService {
	
	private Session session = null;
	private Transaction transaction = null;
	
	/* public List<Episode> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		Anime anime = session.find(Anime.class, animeId);
		anime.getEpisode().size();
		List<Episode> episodes = anime.getEpisode();
		session.close();
		return episodes;
	}*/
	  //SELECT `Anime_anime_id`, `episode_episode_id` FROM `anime_episode` WHERE 1
	 public Set<Episode> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		//*Anime anime = session.find(Anime.class, animeId);
		//anime.getEpisode().size();
		Set<Episode> episodesHashSet = new HashSet<>();
		for(Object oneObject : session.createQuery(
				"Select o from Episode o where o.anime.id = :animeId")
				//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId
				.setParameter("animeId",animeId)
				.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")
				.getResultList()
		) {
			episodesHashSet.add(( Episode ) oneObject);
		}
		session.close();
		return episodesHashSet;
	}
	
	public Set<Episode> getEpisodesOfAnAnime(Long animeId, Long episodeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		Set<Episode> episodesHashSet = new HashSet<>();
		for(Object oneObject : session.createQuery(
				"Select o from Episode o where o.anime.id = :animeId and o.id=:episodeId ")
				.setParameter("animeId",animeId)
				.setParameter("episodeId",episodeId)
				.setHint("org.hibernate.cacheable", true)
				.setCacheRegion("common")
				.getResultList()
		) {
			episodesHashSet.add(( Episode ) oneObject);
		}
		session.close();
		return episodesHashSet;
	}
	
	/*public List<Episode> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		Anime anime = session.find(Anime.class, animeId);
		anime.getEpisode().size();
		List<Episode> episodes = anime.getEpisode();
		session.close();
		return episodes;
	}*/
	
	public Anime getAnimeOfAnEpisode(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		Anime anime = session.find(Anime.class, animeId);
		session.close();
		return anime;
	}
	
	public Episode addEpisode(Long animeId, Episode episode) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Anime anime = session.find(Anime.class, animeId);
		episode.setAnime(anime);
		episode.setParentID(animeId);
		session.save(episode);
		transaction.commit();
		session.close();
		return episode;
	}
	
	public Episode putEpisode(Episode episode) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		session.update(episode);
		transaction.commit();
		session.close();
		return episode;
	}
	
	public void removeEpisode(Long episodeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.getTransaction();
		transaction.begin();
		Episode episode = session.get(Episode.class, episodeId);
		session.delete(episode);
		transaction.commit();
		session.close();
	}
	
	
 
	
	
	/*public List<Episode> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> episodeArrayList = new ArrayList<>();
		for (Object episodeObject : session.createQuery("FROM Episode e LEFT JOIN FETCH e.anime WHERE e.episode=:animeId")
				.setParameter("animeId", animeId)
				.getResultList())
		{//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.parentId=:animeId
			episodeArrayList.add((Episode) episodeObject);
		}
		
		session.close();
		return episodeArrayList;
	}*/
	/*public List<Episode> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> episodeArrayList = new ArrayList<>();
		for (Object episodeObject : session.createQuery("FROM Episode e LEFT JOIN FETCH e.anime WHERE e.parentId=:animeId")
				.setParameter("animeId", animeId)
				.getResultList())
		{//FROM Episode e LEFT JOIN FETCH e.anime WHERE e.parentId=:animeId
			episodeArrayList.add((Episode) episodeObject);
		}
		
		session.close();
		return episodeArrayList;
	}*/
	
	
	// needs optimization
	/*public List<Anime> getAnimeOfAnEpisode(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		List sd =session.createQuery("SELECT e FROM Anime e join fetch e.episode WHERE e.id=:animeId")
				.setParameter("animeId", animeId)
				.setMaxResults(1)
				.getResultList();
		
		session.close();
		return sd;
	}*/
	
	//FROM Episode e LEFT JOIN FETCH e.anime  WHERE e.parentId=:animeId
	/*private String hql = "FROM Episode a LEFT JOIN FETCH a.anime r where r.id=:animeId";
	public List<Episode> getAllEpisodesWithAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> arrayList = new ArrayList<>();
		for (Object oneObject : session.createQuery(hql)
				.setParameter("animeId", animeId)
				.getResultList())
		{//ORDER BY t.updateDate ASC
			arrayList.add((Episode) oneObject);
		}
		session.close();
		return arrayList;
	}
	
	/*public List<String> getAllEpisodesOfAnAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> episodeArrayList = new ArrayList<>();
		//ArrayList<Anime> animeArrayList = new ArrayList<>();
		for (Object episodeObject : session.createQuery("FROM Episode e LEFT JOIN FETCH e.anime WHERE e.parentId=:animeId")
				.setParameter("animeId", animeId)
				.getResultList())
		{
			episodeArrayList.add((Episode) episodeObject);
		}
		*//*for (Object animeObject : session.createQuery("FROM Anime a join a.episode r where r.parentId=:animeId")
				.setParameter("animeId", animeId)
				.getResult
				List())
		{
			 animeArrayList.add(( Anime ) animeObject);
			 //String est = animeObject.toString();
			 
		}*//*
		String toString = episodeArrayList.toString();
		//String toString2 = animeArrayList.toString();
		//String concat = toString.concat(toString2);
		//List<String> items = Arrays.asList(concat.split("\\s*,\\s*"));
		List<String> items = Arrays.asList(toString.split("\\s*,\\s*"));
		
		JSONObject jsonObj = new JSONObject(toString);
		
		session.close();
		return items;
	}*/
	/*private String hql = "FROM Episode a LEFT JOIN FETCH a.anime r where r.id=:animeId";
	public List<Episode> getAllEpisodesWithAnime(Long animeId) {
		session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Episode> arrayList = new ArrayList<>();
		for (Object oneObject : session.createQuery(hql)
				.setParameter("animeId", animeId)
				.getResultList())
		{//ORDER BY t.updateDate ASC
			arrayList.add((Episode) oneObject);
		}
		session.close();
		return arrayList;
	}*/
	
	
	
	/*FROM Episode t JOIN FETCH Anime r WHERE t.parentId=:animeId and r.id=:animeId
	* SELECT new Map(p.nombrePagina, ur.id) FROM User_rol as ur
    INNER JOIN Rol_pagina as rp ON rp.id = ur.id
    INNER JOIN Pagina as p ON p.id = rp.listaWeb_id
    WHERE ur.User_id =:param1 AND p.nombrePagina =:param2
	* */
	
	//FROM Episode a LEFT JOIN FETCH a.anime r where r.id=:animeId query extraña
	//from EmployeeBO join fetch EmployeeBO.department
	//SELECT e FROM Anime e LEFT JOIN FETCH e.episode WHERE e.id=:animeId trae el anime
	//FROM Episode t WHERE t.parentId=:animeId ORDER BY t.updateDate ASC trae anime+cap
	//"FROM Anime a join a.episode r where r.parentId=:animeId" trae el anime del cap
	
	
	
	
	
	/*public Episode addEpisode(long animeId, Episode episode) {
		session = HibernateUtil.getSessionFactory().openSession();
		Anime anime= getalgo(animeId, Anime.class);
		
		//Map<Long, Episode> comments = messages.get(messageId).getComments();
		ArrayList<Anime> arreglo = new ArrayList<>();
		for (Object oneObject : session.createQuery("FROM Anime a ORDER BY a.updateDate DESC")
				.getResultList())
		{
			//arreglo.add((Anime) oneObject);
			//episode.setId( oneObject. );
			//episode.setId(( long ) (arreglo.size()+1));
			// arreglo.set()
		    //arreglo.add(episode.getId(), episode);
		}
		
		session.close();
		return episode;
	}*/
}
