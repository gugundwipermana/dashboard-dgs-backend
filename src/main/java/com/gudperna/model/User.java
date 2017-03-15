package com.gudperna.model;

public class User {
	private int id;
	private int bidang_id;
	private String name;
	private String email;
	private String password;
	private String avatar;
	private String color;

	private Bidang bidang;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBidangId() {
		return bidang_id;
	}

	public void setBidangId(int bidang_id) {
		this.bidang_id = bidang_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}



	public Bidang getBidang() {
		return bidang;
	}

	public void setBidang(Bidang bidang) {
		this.bidang = bidang;
	}
}