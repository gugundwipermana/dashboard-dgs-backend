package com.gudperna.dao.impl;

import com.gudperna.model.Timeline;
import com.gudperna.dao.TimelineDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gudperna.model.User;
import com.gudperna.model.Bidang;
import com.gudperna.model.Unit;
import com.gudperna.model.Division;
import com.gudperna.model.TimelineDetail;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.impl.TimelineDetailDAOImpl;
import com.gudperna.dao.TimelineDetailDAO;

public class TimelineDAOImpl implements TimelineDAO {

	private Connection connection;

	public TimelineDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Timeline> getAll() {
		ArrayList<Timeline> result = new ArrayList<Timeline>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(""+
						"SELECT "+
						" a.*, "+
						" b.NAME USER_NAME, "+
					    " b.AVATAR USER_AVATAR, "+
					    " b.COLOR USER_COLOR, "+
					    " c.NAME BIDANG_NAME, "+
					    " d.NAME UNIT_NAME, "+
					    " e.NAME DIVISION_NAME "+
						"FROM "+
						"  TL_TIMELINES a "+
						"  INNER JOIN TL_USERS b ON a.USER_ID = b.ID "+
						"  INNER JOIN TL_BIDANGS c ON b.BIDANG_ID = c.ID "+
						"  INNER JOIN TL_UNITS d ON c.UNIT_ID = d.ID "+
						"  INNER JOIN TL_DIVISIONS e ON d.DIVISION_ID = e.ID "+
					"");
				while(rs.next()) {
					Timeline timeline = new Timeline();
					timeline.setId(rs.getInt("id"));
					timeline.setUserId(rs.getInt("user_id"));
					timeline.setName(rs.getString("name"));
					timeline.setColor(rs.getString("color"));

					User user = new User();
					user.setName(rs.getString("USER_NAME"));
					user.setAvatar(rs.getString("USER_AVATAR"));
					user.setColor(rs.getString("USER_COLOR"));

					Bidang bidang = new Bidang();
					bidang.setName(rs.getString("BIDANG_NAME"));

					Unit unit = new Unit();
					unit.setName(rs.getString("UNIT_NAME"));

					Division division = new Division();
					division.setName(rs.getString("DIVISION_NAME"));

					unit.setDivision(division);

					bidang.setUnit(unit);

					user.setBidang(bidang);

					timeline.setUser(user);

					result.add(timeline);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public Timeline getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Timeline timeline = new Timeline();
		try {
			ps = connection.prepareStatement(""+
				"SELECT "+
				" a.*, "+
				" b.NAME USER_NAME, "+
				" b.AVATAR USER_AVATAR, "+
				" b.COLOR USER_COLOR, "+
				" c.NAME BIDANG_NAME, "+
				" d.NAME UNIT_NAME, "+
				" e.NAME DIVISION_NAME "+
				"FROM "+
				"  TL_TIMELINES a "+
				"  INNER JOIN TL_USERS b ON a.USER_ID = b.ID "+
				"  INNER JOIN TL_BIDANGS c ON b.BIDANG_ID = c.ID "+
				"  INNER JOIN TL_UNITS d ON c.UNIT_ID = d.ID "+
				"  INNER JOIN TL_DIVISIONS e ON d.DIVISION_ID = e.ID "+
				"WHERE a.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				timeline.setId(rs.getInt("id"));
				timeline.setUserId(rs.getInt("user_id"));
				timeline.setName(rs.getString("name"));
				timeline.setColor(rs.getString("color"));

				User user = new User();
				user.setName(rs.getString("USER_NAME"));
				user.setAvatar(rs.getString("USER_AVATAR"));
				user.setColor(rs.getString("USER_COLOR"));

				Bidang bidang = new Bidang();
				bidang.setName(rs.getString("BIDANG_NAME"));

				Unit unit = new Unit();
				unit.setName(rs.getString("UNIT_NAME"));

				Division division = new Division();
				division.setName(rs.getString("DIVISION_NAME"));
				unit.setDivision(division);
				bidang.setUnit(unit);
				user.setBidang(bidang);
				timeline.setUser(user);

				// HAVE
				TimelineDetailDAO timelineDetailDAOImpl = new TimelineDetailDAOImpl(ConnectionUtil.getConnection());
				timeline.setTimelineDetail(timelineDetailDAOImpl.getByTimeline(id));
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return timeline;
	}

	@Override
	public void insert(Timeline timeline) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_TIMELINES(user_id, name, color) values(?,?,?)");

			ps.setInt(1, timeline.getUserId());
			ps.setString(3, timeline.getName());
			ps.setString(4, timeline.getColor());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Timeline timeline) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_TIMELINES SET user_id=?, name=?, color=? WHERE id=?");
			
			ps.setInt(1, timeline.getUserId());
			ps.setString(2, timeline.getName());
			ps.setString(3, timeline.getColor());

			ps.setInt(4, timeline.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_TIMELINES WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	/**
	 *
	 *
	 */

	public ArrayList<Timeline> getBy(String by, int id) {

		ArrayList<Timeline> result = new ArrayList<Timeline>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(""+
					"SELECT "+
						" a.*, "+
						" b.NAME USER_NAME, "+
					    " b.AVATAR USER_AVATAR, "+
					    " b.COLOR USER_COLOR, "+
					    " c.NAME BIDANG_NAME, "+
					    " d.NAME UNIT_NAME, "+
					    " e.NAME DIVISION_NAME "+
						"FROM "+
						"  TL_TIMELINES a "+
						"  INNER JOIN TL_USERS b ON a.USER_ID = b.ID "+
						"  INNER JOIN TL_BIDANGS c ON b.BIDANG_ID = c.ID "+
						"  INNER JOIN TL_UNITS d ON c.UNIT_ID = d.ID "+
						"  INNER JOIN TL_DIVISIONS e ON d.DIVISION_ID = e.ID "+
						"WHERE "+
						""+by+".ID = "+id);
				while(rs.next()) {

					Timeline timeline = new Timeline();
					timeline.setId(rs.getInt("id"));
					timeline.setUserId(rs.getInt("user_id"));
					timeline.setName(rs.getString("name"));
					timeline.setColor(rs.getString("color"));

					User user = new User();
					user.setName(rs.getString("USER_NAME"));
					user.setAvatar(rs.getString("USER_AVATAR"));
					user.setColor(rs.getString("USER_COLOR"));

					Bidang bidang = new Bidang();
					bidang.setName(rs.getString("BIDANG_NAME"));

					Unit unit = new Unit();
					unit.setName(rs.getString("UNIT_NAME"));

					Division division = new Division();
					division.setName(rs.getString("DIVISION_NAME"));

					unit.setDivision(division);

					bidang.setUnit(unit);

					user.setBidang(bidang);

					timeline.setUser(user);

					result.add(timeline);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(TimelineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}
}