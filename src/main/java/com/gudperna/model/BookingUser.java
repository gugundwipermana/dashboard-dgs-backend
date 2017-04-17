package com.gudperna.model;

public class BookingUser {
	private int id;
	private int booking_id;
	private int user_id;
	private String status;
	private int pengganti;

	// OWNED
	private User user;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getBookingId() {
		return booking_id;
	}
	public void setBookingId(int booking_id) {
		this.booking_id = booking_id;
	}

	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getPengganti() {
		return pengganti;
	}
	public void setPengganti(int pengganti) {
		this.pengganti = pengganti;
	}


	// OWNED
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}