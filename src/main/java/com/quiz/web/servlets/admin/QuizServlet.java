
package com.quiz.web.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.quiz.dao.impl.QuizDaoImpl;
import com.quiz.dao.impl.QuestionDaoImpl;
import com.quiz.model.Quiz;

@WebServlet(name = "QuizServlet", urlPatterns = { "/admin/quiz", "/admin/quiz/new" })
public class QuizServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/admin/quiz/new".equals(path)) {
			req.setAttribute("allQuestions", new QuestionDaoImpl().listAll(0, 200));
			req.getRequestDispatcher("/WEB-INF/views/admin/quiz-manager.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/admin/quiz/new");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String name = req.getParameter("name");
		String[] qIds = req.getParameterValues("questionIds");
		Quiz q = new Quiz();
		q.setName(name);
		q.setPublished(false);
		int quizId = new QuizDaoImpl().create(q);
		if (qIds != null) {
			List<Integer> ids = new ArrayList<>();
			for (String s : qIds)
				ids.add(Integer.parseInt(s));
			int order = 1;
			for (int id : ids)
				new QuizDaoImpl().addQuestion(quizId, id, order++);
			q.setQuestionIds(ids);
		}
		if ("publish".equals(action))
			new QuizDaoImpl().publish(quizId, true);
		req.setAttribute("quiz", new QuizDaoImpl().findById(quizId));
		req.setAttribute("quizQuestions", new QuestionDaoImpl().listAll(0, 200));
		req.getRequestDispatcher("/WEB-INF/views/admin/quiz-manager.jsp").forward(req, resp);
	}
}
