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
	private static final long serialVersionUID = -517339196631236515L;

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
	@ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn( name = "serie_id" ,updatable = false )
	private List<Serie> series;

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	 /*@JsonbTransient
	@OneToMany( fetch = FetchType.EAGER, mappedBy = "series", cascade = CascadeType.ALL )
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
	private List<Serie> animes = new ArrayList<>();*/

	/*@OneToMany( fetch = FetchType.EAGER, mappedBy = "tag", cascade = CascadeType.ALL )
	private List<Serie> series = new ArrayList<>();

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
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
