
package com.quiz.web.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import com.quiz.config.DBConnection;

@WebServlet(name = "AdminDashboardServlet", urlPatterns = { "/admin/dashboard" })
public class AdminDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int totalQuizzes = 0, totalQuestions = 0, totalUsers = 0;
		try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement()) {
			try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM quizzes")) {
				if (rs.next())
					totalQuizzes = rs.getInt(1);
			}
			try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM questions")) {
				if (rs.next())
					totalQuestions = rs.getInt(1);
			}
			try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM users")) {
				if (rs.next())
					totalUsers = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("stats.totalQuizzes", totalQuizzes);
		req.setAttribute("stats.totalQuestions", totalQuestions);
		req.setAttribute("stats.totalUsers", totalUsers);
		req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
	}
}
