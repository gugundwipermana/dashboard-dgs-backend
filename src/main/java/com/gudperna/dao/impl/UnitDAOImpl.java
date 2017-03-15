package com.gudperna.dao.impl;

import com.gudperna.model.Unit;
import com.gudperna.dao.UnitDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnitDAOImpl implements UnitDAO {

	private Connection connection;

	public UnitDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Unit> getAll() {
		ArrayList<Unit> result = new ArrayList<Unit>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_UNITS");
				while(rs.next()) {
					Unit unit = new Unit();
					unit.setId(rs.getInt("id"));
					unit.setDivisionId(rs.getInt("division_id"));
					unit.setName(rs.getString("name"));
					result.add(unit);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(UnitDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public Unit getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Unit unit = new Unit();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_UNITS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				unit.setId(id);
				unit.setDivisionId(rs.getInt("division_id"));
				unit.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UnitDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return unit;
	}

	@Override
	public void insert(Unit unit) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_UNITS(division_id, name) values(?,?)");
			ps.setInt(1, unit.getDivisionId());
			ps.setString(2, unit.getName());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UnitDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Unit unit) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_UNITS SET division_id=?, name=? WHERE id=?");
			ps.setInt(1, unit.getDivisionId());
			ps.setString(2, unit.getName());
			ps.setInt(3, unit.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UnitDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_UNITS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UnitDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}