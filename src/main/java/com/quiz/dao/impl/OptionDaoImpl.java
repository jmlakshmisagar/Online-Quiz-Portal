
package com.quiz.dao.impl;

import com.quiz.config.DBConnection;
import com.quiz.dao.OptionDao;
import com.quiz.model.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDaoImpl implements OptionDao {

	public int create(Option o) {
		String sql = "INSERT INTO options(question_id, option_text, is_correct) VALUES(?,?,?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, o.getQuestionId());
			ps.setString(2, o.getOptionText());
			ps.setBoolean(3, o.isCorrect());
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

	public List<Option> findByQuestionId(int questionId) {
		String sql = "SELECT id, question_id, option_text, is_correct FROM options WHERE question_id = ? ORDER BY id ASC";
		List<Option> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, questionId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Option o = new Option();
					o.setId(rs.getInt("id"));
					o.setQuestionId(rs.getInt("question_id"));
					o.setOptionText(rs.getString("option_text"));
					o.setCorrect(rs.getBoolean("is_correct"));
					list.add(o);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
