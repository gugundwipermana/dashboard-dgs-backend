package com.gudperna.dao;

import com.gudperna.model.Booking;
import java.util.ArrayList;

public interface BookingDAO {
	public ArrayList<Booking> getAll();
	public Booking getById(int id);
	public void insert(Booking booking);
	public void edit(Booking booking);
	public void delete(int id);
}