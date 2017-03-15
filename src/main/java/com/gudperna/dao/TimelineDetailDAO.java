package com.gudperna.dao;

import com.gudperna.model.TimelineDetail;
import java.util.ArrayList;

public interface TimelineDetailDAO {
	public ArrayList<TimelineDetail> getAll();
	public TimelineDetail getById(int id);
	public void insert(TimelineDetail timelineDetail);
	public void edit(TimelineDetail timelineDetail);
	public void delete(int id);

	public ArrayList<TimelineDetail> getByTimeline(int id);
}