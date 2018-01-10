package entitys;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="event")
public class EventEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventid;
	@Column
	private String name;
	@Column
	private String category;
	@Column
	private Date date[];
	@Column
	private String location;
	@Column
	private double raiting;

	
	@ManyToMany(mappedBy = "eventResult")
	private List<ProfileEntity> eventResult;
	@ManyToMany
	@JoinTable(name="event_tag",joinColumns={
			@JoinColumn(name="eventid",referencedColumnName="eventid")},inverseJoinColumns={
					@JoinColumn(name="tagid",referencedColumnName="tagid")})
	private List<TagEntity>tagResult;

	public EventEntity(){}
	
		public EventEntity(int eventid, String name, String category, Date[] date, String location, double raiting) {
			this.eventid = eventid;
			this.name = name;
			this.category = category;
			this.date = date;
			this.location = location;
			this.raiting = raiting;
			
			}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaregory() {
		return category;
	}

	public void setCaregory(String category) {
		this.category = category;
	}

	public double getRaiting() {
		return raiting;
	}

	public void setRaiting(double raiting) {
		this.raiting = raiting;
	}

	public Date[] getDate() {
		return date;
	}

	public void setDate(Date[] date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<ProfileEntity> getEventResult() {
		return eventResult;
	}

	public void setEventResult(List<ProfileEntity> eventResult) {
		this.eventResult = eventResult;
	}

	public List<TagEntity> getTagResult() {
		return tagResult;
	}

	public void setTagResult(List<TagEntity> tagResult) {
		this.tagResult = tagResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
