package com.gudperna.model;

public class File {
	private int id;
	private int timeline_id;
	private String attach;

	private Timeline timeline;

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

	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}



	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

}