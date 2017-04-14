package com.gudperna.model;

import java.util.ArrayList;

public class Booking {
	private int id;
	private int meeting_room_id;
	private int user_id;
	private String dateStart;
	private String dateEnd;

	private MeetingRoom meetingRoom;
	private User user;

	// HAVE
	private ArrayList<BookingUser> bookingUser;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getMeetingRoomId() {
		return meeting_room_id;
	}
	public void setMeetingRoomId(int meeting_room_id) {
		this.meeting_room_id = meeting_room_id;
	}

	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
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



	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<BookingUser> getBookingUser() {
		return bookingUser;
	}

	public void setBookingUser(ArrayList<BookingUser> bookingUser) {
		this.bookingUser = bookingUser;
	}

}