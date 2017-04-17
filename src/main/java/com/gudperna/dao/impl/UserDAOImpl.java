package com.gudperna.dao.impl;

import com.gudperna.model.User;
import com.gudperna.dao.UserDAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<User> getAll() {
		ArrayList<User> result = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			if(connection != null) {
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TL_USERS");
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setBidangId(rs.getInt("bidang_id"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setColor(rs.getString("color"));
					result.add(user);
				}
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public User getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = new User();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_USERS WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(id);
				user.setBidangId(rs.getInt("bidang_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setColor(rs.getString("color"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return user;
	}

	@Override
	public void insert(User user) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO TL_USERS(bidang_id, name, email, password, avatar, color) values(?,?,?,?,?,?)");

			ps.setInt(1, user.getBidangId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAvatar());
			ps.setString(6, user.getColor());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(User user) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE TL_USERS SET bidang_id=?, name=?, email=?, password=?, avatar=?, color=? WHERE id=?");
			
			ps.setInt(1, user.getBidangId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAvatar());
			ps.setString(6, user.getColor());

			ps.setInt(7, user.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM TL_USERS WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	/**
	 * ----------------------------------
	 * For Authentication
	 * ----------------------------------
	 * cekUser()
	 * getByEmail()
	 *
	 */
	public boolean cekUser(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean user_exists = false;

		try {
			ps = connection.prepareStatement("SELECT * FROM TL_USERS WHERE email = ? AND password = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}

		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

	 	return false;
	}

	public User getByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			ps = connection.prepareStatement("SELECT * FROM TL_USERS WHERE email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

		return user;
	}


	/**
	 * ----------------------------------
	 * For Filter
	 * ----------------------------------
	 * getBidangIdByEmail()
	 * getUnitIdByEmail()
	 * getDivisiIdByEmail()
	 *
	 */

	// select
	//   a.EMAIL,
	//   b.ID,
	//   c.ID,
	//   d.ID
	// from 
	//   TL_USERS a
	//   INNER JOIN TL_BIDANGS b ON a.BIDANG_ID = b.ID 
	//   INNER JOIN TL_UNITS c ON b.UNIT_ID = c.ID
	//   INNER JOIN TL_DIVISIONS d ON c.DIVISION_ID = d.ID 
	// ;

	public int getFilterIdByEmail(String by, String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			ps = connection.prepareStatement("SELECT "+
				""+by+".ID "+
				"FROM "+
				"	TL_USERS a "+
				"	INNER JOIN TL_BIDANGS c ON a.BIDANG_ID = c.ID "+
				"	INNER JOIN TL_UNITS d ON c.UNIT_ID = d.ID "+
				"	INNER JOIN TL_DIVISIONS e ON d.DIVISION_ID = e.ID "+
				"WHERE a.email = ? "+
				"");

			ps.setString(1, email);
			rs = ps.executeQuery();

			if(rs.next()) {
				id = rs.getInt("ID");
			}

		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

		return id;
	}
	
}