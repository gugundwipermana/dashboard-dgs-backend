package com.gudperna.model;

import java.util.ArrayList;

public class Timeline {
	private int id;
	private int user_id;
	private String name;
	private String color;

	// OWNED
	private User user;
	// HAVE
	private ArrayList<TimelineDetail> timelineDetail;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


	// OWNED
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	// HAVE
	public ArrayList<TimelineDetail> getTimelineDetail() {
		return timelineDetail;
	}

	public void setTimelineDetail(ArrayList<TimelineDetail> timelineDetail) {
		this.timelineDetail = timelineDetail;
	}

}