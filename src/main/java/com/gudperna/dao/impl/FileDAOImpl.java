package com.gudperna.dao.impl;

import com.gudperna.model.Filez;
import com.gudperna.dao.FileDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDAOImpl implements FileDAO {

	private Connection connection;

	public FileDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Filez> getAll() {
		ArrayList<Filez> result = new ArrayList<Filez>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_FILES");
				while(rs.next()) {
					Filez file = new Filez();
					file.setId(rs.getInt("id"));
					file.setTimelineId(rs.getInt("timeline_id"));
					file.setAttach(rs.getString("attach"));
					result.add(file);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	public Filez getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Filez file = new Filez();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_FILES WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				file.setId(id);
				file.setTimelineId(rs.getInt("timeline_id"));
				file.setAttach(rs.getString("attach"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return file;
	}

	@Override
	public void insert(Filez file) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_FILES(timeline_id, attach) values(?,?)");

			ps.setInt(1, file.getTimelineId());
			ps.setString(2, file.getAttach());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Filez file) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_FILES SET timeline_id=?, attach=? WHERE id=?");
			
			ps.setInt(1, file.getTimelineId());
			ps.setString(2, file.getAttach());

			ps.setInt(3, file.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_FILES WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}



	/**
	 *
	 *
	 */

	@Override
	public ArrayList<Filez> getByTimelineDetail(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Filez> result = new ArrayList<Filez>();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_FILES WHERE timeline_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Filez file = new Filez();
				file.setId(rs.getInt("id"));
				file.setTimelineId(rs.getInt("timeline_id"));
				file.setAttach(rs.getString("attach"));
				result.add(file);
			}
		} catch(SQLException ex) {
			Logger.getLogger(FileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}
}