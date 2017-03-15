package com.gudperna.dao;

import com.gudperna.model.BookingUser;
import java.util.ArrayList;

public interface BookingUserDAO {
	public ArrayList<BookingUser> getAll();
	public BookingUser getById(int id);
	public void insert(BookingUser bookingUser);
	public void edit(BookingUser bookingUser);
	public void delete(int id);

	public ArrayList<BookingUser> getByBooking(int id);
}