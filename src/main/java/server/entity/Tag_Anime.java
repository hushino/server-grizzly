package server.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "animeTag")
@Cacheable( true )
@DynamicUpdate()
public class Tag_Anime implements Serializable {
	
	@Id
	@ManyToOne
	private Anime anime;
	
	@Id
	@ManyToOne
	private Tag tag;
	
	public Tag_Anime() {
	}
	
	
	
}
