package com.gudperna.model;

public class Bidang {
	private int id;
	private int unit_id;
	private String name;

	private Unit unit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnitId() {
		return unit_id;
	}

	public void setUnitId(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}