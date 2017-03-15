package com.gudperna.dao;

import com.gudperna.model.Division;
import java.util.ArrayList;

public interface DivisionDAO {
	public ArrayList<Division> getAll();
	public Division getById(int id);
	public void insert(Division division);
	public void edit(Division division);
	public void delete(int id);
}