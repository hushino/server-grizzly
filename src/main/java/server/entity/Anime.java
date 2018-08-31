package server.entity;


import org.hibernate.annotations.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
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
	
	@Column(length = 1500)
	private String synopsis;
	
	private String state;
	
	private String type;
	
	private String frontimage;
	
	private String backgroundimage;
	
	private LocalDateTime fechadeEmision;
	
	private LocalDateTime fechadeFinalizacion;
	
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
	
	
	@JsonbTransient
	@NotFound( action = NotFoundAction.IGNORE )
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "anime", cascade = CascadeType.ALL )
	private List<Tag_Anime> animes = new ArrayList<>();
	
	
	
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
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFrontimage() {
		return frontimage;
	}
	
	public void setFrontimage(String frontimage) {
		this.frontimage = frontimage;
	}
	
	public String getBackgroundimage() {
		return backgroundimage;
	}
	
	public void setBackgroundimage(String backgroundimage) {
		this.backgroundimage = backgroundimage;
	}
	
	
	public LocalDateTime getFechadeEmision() {
		return fechadeEmision;
	}
	
	public void setFechadeEmision(LocalDateTime fechadeEmision) {
		this.fechadeEmision = fechadeEmision;
	}
	
	public LocalDateTime getFechadeFinalizacion() {
		return fechadeFinalizacion;
	}
	
	public void setFechadeFinalizacion(LocalDateTime fechadeFinalizacion) {
		this.fechadeFinalizacion = fechadeFinalizacion;
	}
	
	public Anime(String title, String synopsis, String state, String type, String frontimage, String backgroundimage, LocalDateTime fechadeEmision, LocalDateTime fechadeFinalizacion, List<Episode> episode) {
		this.title = title;
		this.synopsis = synopsis;
		this.state = state;
		this.type = type;
		this.frontimage = frontimage;
		this.backgroundimage = backgroundimage;
		this.fechadeEmision = fechadeEmision;
		this.fechadeFinalizacion = fechadeFinalizacion;
		this.episode = episode;
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
				/*", episode="+episode+*/
				", createDate="+createDate+
				", updateDate="+updateDate+
				'}';
	}
}
