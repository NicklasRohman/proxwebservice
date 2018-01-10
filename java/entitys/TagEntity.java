package entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class TagEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tagid")
	private int tagid;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "tagResult")
	private List<EventEntity> tagResult;

	public TagEntity(){
	}
	
	public int getId() {
		return tagid;
	}

	public void setId(int id) {
		this.tagid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EventEntity> getTagResult() {
		return tagResult;
	}

	public void setTagResult(List<EventEntity> tagResult) {
		this.tagResult = tagResult;
	}
	
}
