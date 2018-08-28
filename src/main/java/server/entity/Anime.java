package server.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anime")
@Cacheable( true )
@DynamicUpdate()
public class Anime implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "anime_id")
	private Long id;
	
	private String title;
	
	private String data;
	
	@JsonbTransient
	public List<Episode> getEpisode() {
		return episode;
	}
	
	@JsonbTransient
	public void setEpisode(List<Episode> episode) {
		this.episode = episode;
	}
	
	// use optional=false (much faster) @OneToMany(optional = false)
	@JsonbTransient
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "anime", cascade = CascadeType.ALL )
	private List<Episode> episode = new ArrayList<>();
	
 
	
	
	/*@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JoinTable(name = "ANIME_EPISODE", joinColumns = { @JoinColumn(name = "ANIME_ID") },
				inverseJoinColumns = {@JoinColumn(name = "EPISODE_ID") })
		private List<Episode> episodes = new ArrayList<Episode>();
		
		public List<Episode> getEpisodes() {
			return this.episodes;
		}
		
		public void setEpisodes(List<Episode> episodes) {
			this.episodes = episodes;
		}
		*/
	public Anime(String title, String data) {
		this.title = title;
		this.data = data;
	}
	
	public Anime(){
	
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
	
	@Override
	public String toString() {
		return "Anime{"+
				"id="+id+
				", title='"+title+'\''+
				", data='"+data+'\''+
				/*", episode="+episode+*/
				", createDate="+createDate+
				", updateDate="+updateDate+
				'}';
	}
}
