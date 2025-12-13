
package com.quiz.web.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.QuestionDaoImpl;
import com.quiz.model.Question;

@WebServlet(name = "QuestionServlet", urlPatterns = { "/admin/questions" })
public class QuestionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("questions", new QuestionDaoImpl().listAll(0, 100));
		req.getRequestDispatcher("/WEB-INF/views/admin/questions.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("text");
		Question q = new Question();
		q.setText(text);
		new QuestionDaoImpl().create(q);
		resp.sendRedirect(req.getContextPath() + "/admin/questions");
	}
}
