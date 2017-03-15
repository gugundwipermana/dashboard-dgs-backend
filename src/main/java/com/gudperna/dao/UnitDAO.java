package com.gudperna.dao;

import com.gudperna.model.Unit;
import java.util.ArrayList;

public interface UnitDAO {
	public ArrayList<Unit> getAll();
	public Unit getById(int id);
	public void insert(Unit unit);
	public void edit(Unit unit);
	public void delete(int id);
}