
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.UserDaoImpl;
import com.quiz.model.User;
import com.quiz.web.util.PasswordUtil;

@WebServlet(name = "RegisterServlet", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String fullName = req.getParameter("fullName");
		String password = req.getParameter("password");
		byte[] salt = PasswordUtil.generateSalt(32);
		byte[] hash = PasswordUtil.hash(password, salt);
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setFullName(fullName);
		u.setSalt(salt);
		u.setPasswordHash(hash);
		u.setRoleId(2);
		int id = new UserDaoImpl().create(u);
		if (id > 0) {
			u.setId(id);
			HttpSession s = req.getSession(true);
			s.setAttribute("user", u);
			resp.sendRedirect(req.getContextPath() + "/quizzes");
		} else {
			req.setAttribute("error", "Registration failed");
			req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
		}
	}
}
