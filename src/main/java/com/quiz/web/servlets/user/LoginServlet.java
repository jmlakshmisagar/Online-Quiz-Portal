
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.UserDaoImpl;
import com.quiz.model.User;
import com.quiz.web.util.PasswordUtil;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User u = new UserDaoImpl().findByUsername(username);
		if (u != null && PasswordUtil.verify(password, u.getSalt(), u.getPasswordHash())) {
			HttpSession s = req.getSession(true);
			s.setAttribute("user", u);
			resp.sendRedirect(req.getContextPath() + "/quizzes");
		} else {
			req.setAttribute("error", "Invalid credentials");
			req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
		}
	}
}
