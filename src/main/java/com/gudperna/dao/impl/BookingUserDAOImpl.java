package com.gudperna.dao.impl;

import com.gudperna.model.BookingUser;
import com.gudperna.dao.BookingUserDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gudperna.model.User;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingUserDAOImpl implements BookingUserDAO {

	private Connection connection;

	public BookingUserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<BookingUser> getAll() {
		ArrayList<BookingUser> result = new ArrayList<BookingUser>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_BOOKING_USER");
				while(rs.next()) {
					BookingUser bookingUser = new BookingUser();
					bookingUser.setId(rs.getInt("id"));
					bookingUser.setBookingId(rs.getInt("booking_id"));
					bookingUser.setUserId(rs.getInt("user_id"));
					bookingUser.setStatus(rs.getString("status"));
					bookingUser.setPengganti(rs.getInt("pengganti"));
					result.add(bookingUser);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public BookingUser getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		BookingUser bookingUser = new BookingUser();
		try {
			ps = connection.prepareStatement("SELECT a.*, "+
				"	b.NAME USER_NAME, "+
				"	b.AVATAR USER_AVATAR, "+
				"	b.COLOR USER_COLOR "+
				"FROM "+
				"	TL_BOOKING_USER a "+
				"	INNER JOIN TL_USERS b ON a.user_id = b.ID "+ 
				"WHERE a.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				bookingUser.setId(id);
				bookingUser.setBookingId(rs.getInt("booking_id"));
				bookingUser.setUserId(rs.getInt("user_id"));
				bookingUser.setStatus(rs.getString("status"));
				bookingUser.setPengganti(rs.getInt("pengganti"));

				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setAvatar(rs.getString("USER_AVATAR"));
				user.setColor(rs.getString("USER_COLOR"));

				bookingUser.setUser(user);
			}
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return bookingUser;
	}

	@Override
	public void insert(BookingUser bookingUser) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_BOOKING_USER(booking_id, user_id, status, pengganti) values(?,?,?,?)");

			ps.setInt(1, bookingUser.getBookingId());
			ps.setInt(2, bookingUser.getUserId());
			ps.setString(3, bookingUser.getStatus());
			ps.setInt(4, bookingUser.getPengganti());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(BookingUser bookingUser) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_BOOKING_USER SET booking_id=?, user_id=?, status=?, pengganti=? WHERE id=?");
			
			ps.setInt(1, bookingUser.getBookingId());
			ps.setInt(2, bookingUser.getUserId());
			ps.setString(3, bookingUser.getStatus());
			ps.setInt(4, bookingUser.getPengganti());

			ps.setInt(5, bookingUser.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_BOOKING_USER WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	/**
	 *
	 *
	 */

	@Override
	public ArrayList<BookingUser> getByBooking(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<BookingUser> result = new ArrayList<BookingUser>();
		
		try {
			ps = connection.prepareStatement("SELECT a.*, "+
				"	b.NAME USER_NAME, "+
				"	b.AVATAR USER_AVATAR, "+
				"	b.COLOR USER_COLOR "+
				"FROM "+
				"	TL_BOOKING_USER a "+
				"	INNER JOIN TL_USERS b ON a.user_id = b.ID "+
				"WHERE a.booking_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				BookingUser bookingUser = new BookingUser();
				bookingUser.setId(rs.getInt("id"));
				bookingUser.setBookingId(rs.getInt("booking_id"));
				bookingUser.setUserId(rs.getInt("user_id"));
				bookingUser.setStatus(rs.getString("status"));
				bookingUser.setPengganti(rs.getInt("pengganti"));

				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setAvatar(rs.getString("USER_AVATAR"));
				user.setColor(rs.getString("USER_COLOR"));

				bookingUser.setUser(user);

				result.add(bookingUser);
			}
		} catch(SQLException ex) {
			Logger.getLogger(BookingUserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}
}