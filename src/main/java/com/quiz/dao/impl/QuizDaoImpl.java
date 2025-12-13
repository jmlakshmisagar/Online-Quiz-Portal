
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.QuizDao;
import com.quiz.model.Quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoImpl implements QuizDao {

	public int create(Quiz quiz) {
		String sql = "INSERT INTO quizzes(name, published, created_by) VALUES(?,?,?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, quiz.getName());
			ps.setBoolean(2, quiz.isPublished());
			if (quiz.getCreatedBy() == null)
				ps.setNull(3, Types.INTEGER);
			else
				ps.setInt(3, quiz.getCreatedBy());
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

	public void addQuestion(int quizId, int questionId, int displayOrder) {
		String sql = "INSERT INTO quiz_questions(quiz_id, question_id, display_order) VALUES(?,?,?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, quizId);
			ps.setInt(2, questionId);
			ps.setInt(3, displayOrder);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Quiz findById(int id) {
		String sql = "SELECT id, name, published, created_by, created_at FROM quizzes WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Quiz q = new Quiz();
					q.setId(rs.getInt("id"));
					q.setName(rs.getString("name"));
					q.setPublished(rs.getBoolean("published"));
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

	public List<Quiz> listPublished() {
		String sql = "SELECT id, name FROM quizzes WHERE published = TRUE ORDER BY id DESC";
		List<Quiz> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Quiz q = new Quiz();
				q.setId(rs.getInt("id"));
				q.setName(rs.getString("name"));
				q.setPublished(true);
				list.add(q);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public void publish(int quizId, boolean published) {
		String sql = "UPDATE quizzes SET published = ? WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setBoolean(1, published);
			ps.setInt(2, quizId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
