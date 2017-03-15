package com.gudperna.dao.impl;

import com.gudperna.model.Bidang;
import com.gudperna.dao.BidangDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BidangDAOImpl implements BidangDAO {

	private Connection connection;

	public BidangDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Bidang> getAll() {
		ArrayList<Bidang> result = new ArrayList<Bidang>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_BIDANGS");
				while(rs.next()) {
					Bidang bidang = new Bidang();
					bidang.setId(rs.getInt("id"));
					bidang.setUnitId(rs.getInt("unit_id"));
					bidang.setName(rs.getString("name"));
					result.add(bidang);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(BidangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public Bidang getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Bidang bidang = new Bidang();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_BIDANGS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				bidang.setId(id);
				bidang.setUnitId(rs.getInt("unit_id"));
				bidang.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(BidangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return bidang;
	}

	@Override
	public void insert(Bidang bidang) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_BIDANGS(unit_id, name) values(?,?)");
			ps.setInt(1, bidang.getUnitId());
			ps.setString(2, bidang.getName());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BidangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(Bidang bidang) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_BIDANGS SET unit_id=?, name=? WHERE id=?");
			ps.setInt(1, bidang.getUnitId());
			ps.setString(2, bidang.getName());
			ps.setInt(3, bidang.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BidangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_BIDANGS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(BidangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}