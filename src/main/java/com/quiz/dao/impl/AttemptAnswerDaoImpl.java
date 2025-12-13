
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.AttemptAnswerDao;
import com.quiz.model.AttemptAnswer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptAnswerDaoImpl implements AttemptAnswerDao {

	public void upsert(AttemptAnswer aa) {
		String sql = "INSERT INTO attempt_answers(attempt_id, question_id, selected_option_id, is_correct) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE selected_option_id = VALUES(selected_option_id), is_correct = VALUES(is_correct)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, aa.getAttemptId());
			ps.setInt(2, aa.getQuestionId());
			if (aa.getSelectedOptionId() == null)
				ps.setNull(3, Types.INTEGER);
			else
				ps.setInt(3, aa.getSelectedOptionId());
			if (aa.getCorrect() == null)
				ps.setNull(4, Types.BOOLEAN);
			else
				ps.setBoolean(4, aa.getCorrect());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AttemptAnswer> listByAttempt(int attemptId) {
		String sql = "SELECT attempt_id, question_id, selected_option_id, is_correct FROM attempt_answers WHERE attempt_id = ?";
		List<AttemptAnswer> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, attemptId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					AttemptAnswer aa = new AttemptAnswer();
					aa.setAttemptId(rs.getInt("attempt_id"));
					aa.setQuestionId(rs.getInt("question_id"));
					int sel = rs.getInt("selected_option_id");
					aa.setSelectedOptionId(rs.wasNull() ? null : sel);
					boolean c = rs.getBoolean("is_correct");
					aa.setCorrect(rs.wasNull() ? null : c);
					list.add(aa);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
