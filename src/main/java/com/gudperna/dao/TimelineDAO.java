package com.gudperna.dao;

import com.gudperna.model.Timeline;
import java.util.ArrayList;

public interface TimelineDAO {
	public ArrayList<Timeline> getAll();
	public Timeline getById(int id);
	public void insert(Timeline timeline);
	public void edit(Timeline timeline);
	public void delete(int id);

	public ArrayList<Timeline> getBy(String by, int id);
}