package com.gudperna.dao.impl;

import com.gudperna.model.Booking;
import com.gudperna.dao.BookingDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDAOImpl implements BookingDAO {

	private Connection connection;

	public BookingDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Booking> getAll() {
		ArrayList<Booking> result = new ArrayList<Booking>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_BOOKING");
				while(rs.next()) {
					Booking booking = new Booking();
					booking.setId(rs.getInt("id"));
					booking.setMeetingRoomId(rs.getInt("meeting_room_id"));
					booking.setUserId(rs.getInt("user_id"));
					booking.setDateStart(rs.getString("date_start"));
					booking.setDateEnd(rs.getString("date_end"));
					result.add(booking);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public Booking getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Booking booking = new Booking();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_BOOKING WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				booking.setId(id);
				booking.setMeetingRoomId(rs.getInt("meeting_room_id"));
				booking.setUserId(rs.getInt("user_id"));
				booking.setDateStart(rs.getString("date_start"));
				booking.setDateEnd(rs.getString("date_end"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return booking;
	}

	@Override
	public void insert(Booking booking) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_BOOKING(meeting_room_id, user_id, date_start, date_end) values(?,?,?,?)");

			ps.setInt(1, booking.getMeetingRoomId());
			ps.setInt(2, booking.getUserId());
			ps.setString(3, booking.getDateStart());
			ps.setString(4, booking.getDateEnd());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Booking booking) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_BOOKING SET meeting_room_id=?, user_id=?, date_start=?, date_end=? WHERE id=?");
			
			ps.setInt(1, booking.getMeetingRoomId());
			ps.setInt(2, booking.getUserId());
			ps.setString(3, booking.getDateStart());
			ps.setString(4, booking.getDateEnd());

			ps.setInt(5, booking.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_BOOKING WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}