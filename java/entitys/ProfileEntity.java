package entitys;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import dto.ProfileDto;

@Entity
@Table(name="profile")
public class ProfileEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id 
	private String emails;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String bio;
//	@Column
//	private String photo;
//	@Column
//	private double profileRating;
	
	@ManyToMany
	@JoinTable(name="roles_profile",joinColumns={
			@JoinColumn(name="emails",referencedColumnName="emails")}, inverseJoinColumns={
					@JoinColumn(name="rolename",referencedColumnName="rolename")})
	private List<RoleEntity>roles;

	
	@ManyToMany
	@JoinTable(name = "profile_event",joinColumns={
			@JoinColumn(name="emails",referencedColumnName = "emails")}, inverseJoinColumns = {
					@JoinColumn(name="eventid",referencedColumnName="eventid")})
	private List<EventEntity>eventResult;

	public ProfileEntity(){}
	
	public ProfileEntity(ProfileDto dto){
		this.emails = dto.getEmails();
		this.password = dto.getPassword();
		this.name= dto.getName();
		this.bio=dto.getBio();
	}

	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
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

//	public String getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(String photo) {
//		this.photo = photo;
//	}
//
//	public double getProfileRating() {
//		return profileRating;
//	}
//
//	public void setProfileRating(double rating) {
//		this.profileRating = rating;
//	}

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
