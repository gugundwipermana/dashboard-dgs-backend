package com.gudperna.dao;

import com.gudperna.model.Filez;
import java.util.ArrayList;

public interface FileDAO {
	public ArrayList<Filez> getAll();
	public Filez getById(int id);
	public void insert(Filez file);
	public void edit(Filez file);
	public void delete(int id);

	public ArrayList<Filez> getByTimelineDetail(int id);
}