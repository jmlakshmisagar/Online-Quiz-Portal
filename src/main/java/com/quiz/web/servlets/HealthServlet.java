
package com.quiz.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import com.quiz.config.DBConnection;

@WebServlet(name = "HealthServlet", urlPatterns = { "/health" })
public class HealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=UTF-8");
		StringBuilder sb = new StringBuilder();
		sb.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
		sb.append("Servlet API: ").append(getServletContext().getMajorVersion()).append(".")
				.append(getServletContext().getMinorVersion()).append("\n");
		try (Connection con = DBConnection.getConnection()) {
			sb.append("DB Connection: OK\n");
			sb.append("Auto-commit: ").append(con.getAutoCommit()).append("\n");
		} catch (Exception e) {
			sb.append("DB Error: ").append(e.getMessage()).append("\n");
		}
		resp.getWriter().print(sb.toString());
	}
}
