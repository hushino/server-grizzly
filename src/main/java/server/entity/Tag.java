package server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tag")
@Cacheable( true )
@DynamicUpdate()
public class Tag implements Serializable {
	public Tag() {
	}
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id")
	@Id
	private String id;
	
	private String gender;
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	
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
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
}
