package com.gudperna.model;

public class Unit {
	private int id;
	private int division_id;
	private String name;

	private Division division;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDivisionId() {
		return division_id;
	}

	public void setDivisionId(int division_id) {
		this.division_id = division_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
}