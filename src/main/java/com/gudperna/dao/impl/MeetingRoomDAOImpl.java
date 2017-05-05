package com.gudperna.dao.impl;

import com.gudperna.model.MeetingRoom;
import com.gudperna.dao.MeetingRoomDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeetingRoomDAOImpl implements MeetingRoomDAO {

	private Connection connection;

	public MeetingRoomDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<MeetingRoom> getAll() {
		ArrayList<MeetingRoom> result = new ArrayList<MeetingRoom>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_MEETING_ROOMS");
				while(rs.next()) {
					MeetingRoom meetingRoom = new MeetingRoom();
					meetingRoom.setId(rs.getInt("id"));
					meetingRoom.setName(rs.getString("name"));
					meetingRoom.setCapacity(rs.getInt("capacity"));
					meetingRoom.setImage(rs.getString("image"));
					result.add(meetingRoom);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(MeetingRoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public MeetingRoom getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		MeetingRoom meetingRoom = new MeetingRoom();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_MEETING_ROOMS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				meetingRoom.setId(id);
				meetingRoom.setName(rs.getString("name"));
				meetingRoom.setCapacity(rs.getInt("capacity"));
				meetingRoom.setImage(rs.getString("image"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(MeetingRoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return meetingRoom;
	}

	@Override
	public void insert(MeetingRoom meetingRoom) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_MEETING_ROOMS(name,capacity,image) values(?,?,?)");
			ps.setString(1, meetingRoom.getName());
			ps.setInt(2, meetingRoom.getCapacity());
			ps.setString(3, meetingRoom.getImage());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(MeetingRoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(MeetingRoom meetingRoom) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_MEETING_ROOMS SET name=?, capacity=?, image=? WHERE id=?");
			ps.setString(1, meetingRoom.getName());
			ps.setInt(2, meetingRoom.getCapacity());
			ps.setString(3, meetingRoom.getImage());
			ps.setInt(4, meetingRoom.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(MeetingRoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_MEETING_ROOMS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(MeetingRoomDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}