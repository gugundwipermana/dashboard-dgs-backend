package com.gudperna.dao.impl;

import com.gudperna.model.TimelineDetail;
import com.gudperna.dao.TimelineDetailDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimelineDetailDAOImpl implements TimelineDetailDAO {

	private Connection connection;

	public TimelineDetailDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<TimelineDetail> getAll() {
		ArrayList<TimelineDetail> result = new ArrayList<TimelineDetail>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_TIMELINE_DETAILS");
				while(rs.next()) {
					TimelineDetail timelineDetail = new TimelineDetail();
					timelineDetail.setId(rs.getInt("id"));
					timelineDetail.setTimelineId(rs.getInt("timeline_id"));
					timelineDetail.setUserId(rs.getInt("user_id"));
					timelineDetail.setName(rs.getString("name"));
					timelineDetail.setDescription(rs.getString("description"));
					timelineDetail.setPlaneStart(rs.getString("plane_start"));
					timelineDetail.setPlaneEnd(rs.getString("plane_end"));
					timelineDetail.setDateStart(rs.getString("date_start"));
					timelineDetail.setDateEnd(rs.getString("date_end"));
					timelineDetail.setColor(rs.getString("color"));
					result.add(timelineDetail);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public TimelineDetail getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		TimelineDetail timelineDetail = new TimelineDetail();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_TIMELINE_DETAILS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				timelineDetail.setId(id);
				timelineDetail.setTimelineId(rs.getInt("timeline_id"));
				timelineDetail.setUserId(rs.getInt("user_id"));
				timelineDetail.setName(rs.getString("name"));
				timelineDetail.setDescription(rs.getString("description"));
				timelineDetail.setPlaneStart(rs.getString("plane_start"));
				timelineDetail.setPlaneEnd(rs.getString("plane_end"));
				timelineDetail.setDateStart(rs.getString("date_start"));
				timelineDetail.setDateEnd(rs.getString("date_end"));
				timelineDetail.setColor(rs.getString("color"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return timelineDetail;
	}

	@Override
	public void insert(TimelineDetail timelineDetail) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_TIMELINE_DETAILS(timeline_id, user_id, name, description, plane_start, plane_end, date_start, date_end, color) values(?,?,?,?,?,?,?,?,?)");

			ps.setInt(1, timelineDetail.getTimelineId());
			ps.setInt(2, timelineDetail.getUserId());
			ps.setString(3, timelineDetail.getName());
			ps.setString(4, timelineDetail.getDescription());
			ps.setString(5, timelineDetail.getPlaneStart());
			ps.setString(6, timelineDetail.getPlaneEnd());
			ps.setString(7, timelineDetail.getDateStart());
			ps.setString(8, timelineDetail.getDateEnd());
			ps.setString(9, timelineDetail.getColor());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(TimelineDetail timelineDetail) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_TIMELINE_DETAILS SET timeline_id=?, user_id=?, name=?, description=?, plane_start=?, plane_end=?, date_start=?, date_end=?, color=? WHERE id=?");
			
			ps.setInt(1, timelineDetail.getTimelineId());
			ps.setInt(2, timelineDetail.getUserId());
			ps.setString(3, timelineDetail.getName());
			ps.setString(4, timelineDetail.getDescription());
			ps.setString(5, timelineDetail.getPlaneStart());
			ps.setString(6, timelineDetail.getPlaneEnd());
			ps.setString(7, timelineDetail.getDateStart());
			ps.setString(8, timelineDetail.getDateEnd());
			ps.setString(9, timelineDetail.getColor());

			ps.setInt(10, timelineDetail.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_TIMELINE_DETAILS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	/**
	 *
	 *
	 */

	public ArrayList<TimelineDetail> getByTimeline(int id) {
		ArrayList<TimelineDetail> result = new ArrayList<TimelineDetail>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		TimelineDetail timelineDetail = new TimelineDetail();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_TIMELINE_DETAILS WHERE timeline_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				timelineDetail.setId(id);
				timelineDetail.setTimelineId(rs.getInt("timeline_id"));
				timelineDetail.setUserId(rs.getInt("user_id"));
				timelineDetail.setName(rs.getString("name"));
				timelineDetail.setDescription(rs.getString("description"));
				timelineDetail.setPlaneStart(rs.getString("plane_start"));
				timelineDetail.setPlaneEnd(rs.getString("plane_end"));
				timelineDetail.setDateStart(rs.getString("date_start"));
				timelineDetail.setDateEnd(rs.getString("date_end"));
				timelineDetail.setColor(rs.getString("color"));
				result.add(timelineDetail);
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

}