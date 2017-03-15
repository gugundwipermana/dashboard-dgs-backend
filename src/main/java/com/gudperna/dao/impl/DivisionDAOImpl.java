package com.gudperna.dao.impl;

import com.gudperna.model.Division;
import com.gudperna.dao.DivisionDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DivisionDAOImpl implements DivisionDAO {

	private Connection connection;

	public DivisionDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Division> getAll() {
		ArrayList<Division> result = new ArrayList<Division>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_DIVISIONS");
				while(rs.next()) {
					Division division = new Division();
					division.setId(rs.getInt("id"));
					division.setName(rs.getString("name"));
					result.add(division);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(DivisionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public Division getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Division division = new Division();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_DIVISIONS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				division.setId(id);
				division.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(DivisionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return division;
	}

	@Override
	public void insert(Division division) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_DIVISIONS(name) values(?)");
			ps.setString(1, division.getName());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(DivisionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Division division) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_DIVISIONS SET name=? WHERE id=?");
			ps.setString(1, division.getName());
			ps.setInt(2, division.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(DivisionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_DIVISIONS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(DivisionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}