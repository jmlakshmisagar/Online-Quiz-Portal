
package com.quiz.web.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.UserDaoImpl;
import com.quiz.model.User;
import com.quiz.web.util.PasswordUtil;

@WebServlet(name = "AdminLoginServlet", urlPatterns = { "/admin/login" })
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User u = new UserDaoImpl().findByUsername(username);
		if (u != null && u.getRoleId() == 1 && PasswordUtil.verify(password, u.getSalt(), u.getPasswordHash())) {
			HttpSession s = req.getSession(true);
			s.setAttribute("admin", u);
			resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
		} else {
			req.setAttribute("error", "Invalid admin credentials");
			req.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(req, resp);
		}
	}
}
