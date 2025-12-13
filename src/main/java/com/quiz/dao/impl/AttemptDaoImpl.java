
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.AttemptDao;
import com.quiz.model.Attempt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptDaoImpl implements AttemptDao {

	public int startAttempt(int quizId, int userId) {
		String sql = "INSERT INTO attempts(quiz_id, user_id) VALUES(?,?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, quizId);
			ps.setInt(2, userId);
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

	public void submitAttempt(int attemptId, int score) {
		String sql = "UPDATE attempts SET submitted_at = CURRENT_TIMESTAMP, score = ? WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, score);
			ps.setInt(2, attemptId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Attempt findById(int id) {
		String sql = "SELECT id, quiz_id, user_id, started_at, submitted_at, score FROM attempts WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Attempt a = new Attempt();
					a.setId(rs.getInt("id"));
					a.setQuizId(rs.getInt("quiz_id"));
					a.setUserId(rs.getInt("user_id"));
					Timestamp st = rs.getTimestamp("started_at");
					Timestamp sb = rs.getTimestamp("submitted_at");
					a.setStartedAt(st != null ? st.toInstant() : null);
					a.setSubmittedAt(sb != null ? sb.toInstant() : null);
					a.setScore(rs.getInt("score"));
					return a;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public List<Attempt> listByQuiz(int quizId, int limit) {
		String sql = "SELECT id, user_id, score, submitted_at FROM attempts WHERE quiz_id = ? AND submitted_at IS NOT NULL ORDER BY score DESC, submitted_at ASC LIMIT ?";
		List<Attempt> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, quizId);
			ps.setInt(2, limit);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Attempt a = new Attempt();
					a.setId(rs.getInt("id"));
					a.setUserId(rs.getInt("user_id"));
					a.setScore(rs.getInt("score"));
					Timestamp sb = rs.getTimestamp("submitted_at");
					a.setSubmittedAt(sb != null ? sb.toInstant() : null);
					list.add(a);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
