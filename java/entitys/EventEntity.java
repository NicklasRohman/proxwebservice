package entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import dto.EventDto;

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
	private String date;
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
	
	public EventEntity(EventDto dto) {
		this.eventid = dto.getEventid();
		this.name = dto.getName();
		this.category = dto.getCatagory();
		this.date = dto.getDate();
		this.location = dto.getLocation();
		this.raiting = dto.getRaiting();
		
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
