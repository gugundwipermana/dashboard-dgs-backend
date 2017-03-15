package com.gudperna.dao;

import com.gudperna.model.File;
import java.util.ArrayList;

public interface FileDAO {
	public ArrayList<File> getAll();
	public File getById(int id);
	public void insert(File file);
	public void edit(File file);
	public void delete(int id);

	public ArrayList<File> getByTimelineDetail(int id);
}