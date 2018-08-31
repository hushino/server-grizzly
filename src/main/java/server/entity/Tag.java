package server.entity;

import org.hibernate.annotations.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	@NotFound( action = NotFoundAction.IGNORE )
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "tag", cascade = CascadeType.ALL)
	private List<Tag_Anime> tagAnimes = new ArrayList<>();
	
	public List<Tag_Anime> getTagAnimes() {
		return tagAnimes;
	}
	
	public void setTagAnimes(List<Tag_Anime> tagAnimes) {
		this.tagAnimes = tagAnimes;
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
