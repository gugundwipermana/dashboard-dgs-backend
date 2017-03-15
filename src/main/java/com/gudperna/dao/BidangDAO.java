package com.gudperna.dao;

import com.gudperna.model.Bidang;
import java.util.ArrayList;

public interface BidangDAO {
	public ArrayList<Bidang> getAll();
	public Bidang getById(int id);
	public void insert(Bidang bidang);
	public void edit(Bidang bidang);
	public void delete(int id);
}