package server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity()
@Table(name = "episode")
@Cacheable( true )
@DynamicUpdate()
public class Episode implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "episode_id")
	private Long id;
	
	private String title;
	
	private double episode;
	
	@Column(length = 900)
	private String imageCap;
	
	@Column(length = 300)
	private String server;
	
	public Long getParentID() {
		return parentID;
	}
	
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	
	@Column(updatable = false)
	private Long parentID; //no columm and acces to joincolumm id
	
	
	//@ElementCollection(targetClass=Anime.class,fetch = FetchType.EAGER)
	
	/*@ManyToMany( mappedBy = "episodes" ,fetch = FetchType.EAGER)
	private List<Anime> anime;
	
	public List<Anime> getAnime() {
		return anime;
	}
	
	public void setAnime(List<Anime> anime) {
		this.anime = anime;
	}
	*/
	// use optional=false (much faster) @OneToMany(optional = false)
	@JsonbTransient
	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn( name = "anime_id" ,updatable = false )
	private Anime anime;
	
	public Anime getAnime() {
		return anime;
	}
	
	@JsonbTransient
	public void setAnime(Anime anime) {
		this.anime = anime;
	}
	
	
	public Episode(String title, double episode, Anime anime) {
		this.title = title;
		this.episode = episode;
		this.anime = anime;
	}
	
	public Episode() {
	}
	public String getImageCap() {
		return imageCap;
	}
	
	public void setImageCap(String imageCap) {
		this.imageCap = imageCap;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long  id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getEpisode() {
		return episode;
	}
	
	public void setEpisode(double episode) {
		this.episode = episode;
	}
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "Episode{"+
				"id="+id+
				", title='"+title+'\''+
				", episode="+episode+
				", anime="+anime+
				", createDate="+createDate+
				", updateDate="+updateDate+
				'}';
	}
}