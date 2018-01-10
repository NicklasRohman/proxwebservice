package dto;

import java.sql.Date;

public class EventDto {

	private int eventid;
	private String name;
	private String catagory;
	private Date[] date;
	private String location;
	private double raiting;

	public EventDto(){}
	
	public EventDto(int eventid, String name, String caregory, Date[] date, String location, double raiting) {
	this.eventid = eventid;
	this.name = name;
	this.catagory = caregory;
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

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
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

	public double getRaiting() {
		return raiting;
	}

	public void setRaiting(double raiting) {
		this.raiting = raiting;
	}

}
