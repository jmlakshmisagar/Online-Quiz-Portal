
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.QuizDaoImpl;

@WebServlet(name = "QuizListServlet", urlPatterns = { "/quizzes" })
public class QuizListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("quizzes", new QuizDaoImpl().listPublished());
		req.getRequestDispatcher("/WEB-INF/views/user/quiz-list.jsp").forward(req, resp);
	}
}
