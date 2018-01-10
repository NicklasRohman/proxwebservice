package entitys;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="profile")
public class ProfileEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profileid")
	private int id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String bio;
	@Column
	private String photo;
	@Column
	private double profileRating;
	
	@ManyToMany
	@JoinTable(name = "profile_event",joinColumns={
			@JoinColumn(name="profileid",referencedColumnName = "profileid")}, inverseJoinColumns = {
					@JoinColumn(name="eventid",referencedColumnName="eventid")})
	private List<EventEntity>eventResult;

	public ProfileEntity(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public double getProfileRating() {
		return profileRating;
	}

	public void setProfileRating(double rating) {
		this.profileRating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<EventEntity> getEventResult() {
		return eventResult;
	}

	public void setEventResult(List<EventEntity> eventResult) {
		this.eventResult = eventResult;
	}
}
