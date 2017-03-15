package com.gudperna.model;

public class TimelineDetail {
	private int id;
	private int timeline_id;
	private int user_id;
	private String name;
	private String description;
	private String planeStart;
	private String planeEnd;
	private String dateStart;
	private String dateEnd;
	private String color;

	private Timeline timeline;
	private User user;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getTimelineId() {
		return timeline_id;
	}
	public void setTimelineId(int timeline_id) {
		this.timeline_id = timeline_id;
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaneStart() {
		return planeStart;
	}
	public void setPlaneStart(String planeStart) {
		this.planeStart = planeStart;
	}

	public String getPlaneEnd() {
		return planeEnd;
	}
	public void setPlaneEnd(String planeEnd) {
		this.planeEnd = planeEnd;
	}

	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}



	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}