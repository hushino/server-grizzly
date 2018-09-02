package server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tag")
@Cacheable( true )
@DynamicUpdate()
public class Tag implements Serializable {
	public Tag() {
	}
	
	@GeneratedValue
	@Id
	@Column(name = "tag_id")
	private Long id;
 
	private String gender;
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonbTransient
	@ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn( name = "anime_id" ,updatable = false )
	private List<Anime> anime;
	
	public List<Anime> getAnime() {
		return anime;
	}
	
	public void setAnime(List<Anime> anime) {
		this.anime = anime;
	}
	 /*@JsonbTransient
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "anime", cascade = CascadeType.ALL )
	private List<Tag> tags = new ArrayList<>();
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}*/
	
	/*@JoinTable(
			name="AnimeTag",
			joinColumns=@JoinColumn(name="AnimeId"),
			inverseJoinColumns=@JoinColumn(name="TagId")
	)*/
	/*@JsonbTransient
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL},mappedBy = "tags")
	private List<Anime> animes = new ArrayList<>();*/
 
	/*@OneToMany( fetch = FetchType.EAGER, mappedBy = "tag", cascade = CascadeType.ALL )
	private List<Anime> anime = new ArrayList<>();
	
	public List<Anime> getAnime() {
		return anime;
	}
	
	public void setAnime(List<Anime> anime) {
		this.anime = anime;
	}*/

	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
}
