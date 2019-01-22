package server.entity;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "serie")
@Cacheable( true )
@DynamicUpdate()
public class Serie implements Serializable {

	private static final long serialVersionUID = 2609060235592553004L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serie_id")
	private Long id;

	private String title;

	@Column(length = 1500)
	private String synopsis;

	private String state;

	private String type;

	private String frontimage;

	private String backgroundimage;

	private String fechadeEmision;

	private String fechadeFinalizacion;



	// use optional=false (much faster) @OneToMany(optional = false)

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "serie", cascade = CascadeType.ALL )
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Episode> episode = new ArrayList<>();

	@ManyToMany( fetch = FetchType.EAGER, mappedBy = "series", cascade = CascadeType.ALL )
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Tag> tags =new ArrayList<>();

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "serie", cascade = CascadeType.ALL )
	@Fetch(value = FetchMode.SUBSELECT)
	private List<User> user = new ArrayList<>();

	public List<Episode> getEpisode() {
		return episode;
	}

	public void setEpisode(List<Episode> episode) {
		this.episode = episode;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	 /*@JoinTable(
		name="AnimeTag",
		joinColumns=@JoinColumn(name="TagId"),
		inverseJoinColumns=@JoinColumn(name="AnimeId")
	)*/
	/*@JsonbTransient
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL} )
	private List<Tag> tags = new ArrayList<>();

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}*/


	/*	@JsonbTransient
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn( name = "tag_id" ,updatable = false )
	private Tag tag;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}*/

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


	public String getFechadeEmision() {
		return fechadeEmision;
	}

	public void setFechadeEmision(String fechadeEmision) {
		this.fechadeEmision = fechadeEmision;
	}

	public String getFechadeFinalizacion() {
		return fechadeFinalizacion;
	}

	public void setFechadeFinalizacion(String fechadeFinalizacion) {
		this.fechadeFinalizacion = fechadeFinalizacion;
	}

	public Serie(String title, String synopsis, String state, String type, String frontimage, String backgroundimage, String fechadeEmision, String fechadeFinalizacion ) {
		this.title = title;
		this.synopsis = synopsis;
		this.state = state;
		this.type = type;
		this.frontimage = frontimage;
		this.backgroundimage = backgroundimage;
		this.fechadeEmision = fechadeEmision;
		this.fechadeFinalizacion = fechadeFinalizacion;
	}

	public Serie(){

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
		return "Serie{"+
				"id="+id+
				", title='"+title+'\''+
				/*", episode="+episode+*/
				", createDate="+createDate+
				", updateDate="+updateDate+
				'}';
	}
}
