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

import com.gudperna.model.User;
import com.gudperna.model.MeetingRoom;
import com.gudperna.model.BookingUser;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.impl.BookingUserDAOImpl;
import com.gudperna.dao.BookingUserDAO;

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
				rs = stmt.executeQuery(
					"SELECT "+
					"  a.*, "+
					"  b.NAME USER_NAME, "+
					"  b.NAME USER_NAME, "+
					"  b.AVATAR USER_AVATAR, "+
					"  b.COLOR USER_COLOR, "+
					"  c.NAME MEETING_ROOM_NAME "+
					"FROM  "+
					"  TL_BOOKING a "+
					"  INNER JOIN TL_USERS b ON a.USER_ID = b.ID "+
					"  INNER JOIN TL_MEETING_ROOMS c ON a.MEETING_ROOM_ID = c.ID ");
				while(rs.next()) {
					Booking booking = new Booking();
					booking.setId(rs.getInt("id"));
					booking.setMeetingRoomId(rs.getInt("meeting_room_id"));
					booking.setUserId(rs.getInt("user_id"));
					booking.setDateStart(rs.getString("date_start"));
					booking.setDateEnd(rs.getString("date_end"));

					User user = new User();
					user.setName(rs.getString("USER_NAME"));
					user.setAvatar(rs.getString("USER_AVATAR"));
					user.setColor(rs.getString("USER_COLOR"));
					booking.setUser(user);

					MeetingRoom meetingRoom = new MeetingRoom();
					meetingRoom.setName(rs.getString("MEETING_ROOM_NAME"));
					booking.setMeetingRoom(meetingRoom);

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
			ps = connection.prepareStatement(
				"SELECT "+
					"  a.*, "+
					"  b.NAME USER_NAME, "+
					"  b.NAME USER_NAME, "+
					"  b.AVATAR USER_AVATAR, "+
					"  b.COLOR USER_COLOR, "+
					"  c.NAME MEETING_ROOM_NAME "+
					"FROM  "+
					"  TL_BOOKING a "+
					"  INNER JOIN TL_USERS b ON a.USER_ID = b.ID "+
					"  INNER JOIN TL_MEETING_ROOMS c ON a.MEETING_ROOM_ID = c.ID "+

				" WHERE a.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				booking.setId(id);
				booking.setMeetingRoomId(rs.getInt("meeting_room_id"));
				booking.setUserId(rs.getInt("user_id"));
				booking.setDateStart(rs.getString("date_start"));
				booking.setDateEnd(rs.getString("date_end"));

				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setAvatar(rs.getString("USER_AVATAR"));
				user.setColor(rs.getString("USER_COLOR"));
				booking.setUser(user);

				MeetingRoom meetingRoom = new MeetingRoom();
				meetingRoom.setName(rs.getString("MEETING_ROOM_NAME"));
				booking.setMeetingRoom(meetingRoom);

				// HAVE
				BookingUserDAO bookingUserDAO = new BookingUserDAOImpl(ConnectionUtil.getConnection());
				booking.setBookingUser(bookingUserDAO.getByBooking(id));
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