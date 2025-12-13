
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.UserDao;
import com.quiz.model.User;
import java.sql.*;

public class UserDaoImpl implements UserDao {

	public User findByUsername(String username) {
		String sql = "SELECT id, username, email, password_hash, salt, full_name, role_id, created_at FROM users WHERE username = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					u.setPasswordHash(rs.getBytes("password_hash"));
					u.setSalt(rs.getBytes("salt"));
					u.setFullName(rs.getString("full_name"));
					u.setRoleId(rs.getInt("role_id"));
					Timestamp ts = rs.getTimestamp("created_at");
					u.setCreatedAt(ts != null ? ts.toInstant() : null);
					return u;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public User findById(int id) {
		String sql = "SELECT id, username, email, password_hash, salt, full_name, role_id, created_at FROM users WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					u.setPasswordHash(rs.getBytes("password_hash"));
					u.setSalt(rs.getBytes("salt"));
					u.setFullName(rs.getString("full_name"));
					u.setRoleId(rs.getInt("role_id"));
					Timestamp ts = rs.getTimestamp("created_at");
					u.setCreatedAt(ts != null ? ts.toInstant() : null);
					return u;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public int create(User user) {
		String sql = "INSERT INTO users(username, email, password_hash, salt, full_name, role_id) VALUES(?,?,?,?,?,?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setBytes(3, user.getPasswordHash());
			ps.setBytes(4, user.getSalt());
			ps.setString(5, user.getFullName());
			ps.setInt(6, user.getRoleId());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}
}
