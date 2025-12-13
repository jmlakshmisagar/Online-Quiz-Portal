
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.QuestionDao;
import com.quiz.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

	public int create(Question q) {
		String sql = "INSERT INTO questions(text, created_by) VALUES(?,?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, q.getText());
			if (q.getCreatedBy() == null)
				ps.setNull(2, Types.INTEGER);
			else
				ps.setInt(2, q.getCreatedBy());
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

	public Question findById(int id) {
		String sql = "SELECT id, text, created_by, created_at FROM questions WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Question q = new Question();
					q.setId(rs.getInt("id"));
					q.setText(rs.getString("text"));
					int cb = rs.getInt("created_by");
					q.setCreatedBy(rs.wasNull() ? null : cb);
					Timestamp ts = rs.getTimestamp("created_at");
					q.setCreatedAt(ts != null ? ts.toInstant() : null);
					return q;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public List<Question> listAll(int offset, int limit) {
		String sql = "SELECT id, text FROM questions ORDER BY id DESC LIMIT ? OFFSET ?";
		List<Question> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Question q = new Question();
					q.setId(rs.getInt("id"));
					q.setText(rs.getString("text"));
					list.add(q);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
