
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.quiz.dao.impl.QuizDaoImpl;
import com.quiz.dao.impl.QuestionDaoImpl;
import com.quiz.dao.impl.OptionDaoImpl;
import com.quiz.dao.impl.AttemptDaoImpl;
import com.quiz.model.Quiz;
import com.quiz.model.Question;

@WebServlet(name = "TakeQuizServlet", urlPatterns = { "/quiz/take" })
public class TakeQuizServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int quizId = Integer.parseInt(req.getParameter("id"));
		Quiz quiz = new QuizDaoImpl().findById(quizId);
		HttpSession s = req.getSession();
		Integer attemptId = (Integer) s.getAttribute("attemptId_" + quizId);
		if (attemptId == null) {
			attemptId = new AttemptDaoImpl().startAttempt(quizId,
					((com.quiz.model.User) s.getAttribute("user")).getId());
			s.setAttribute("attemptId_" + quizId, attemptId);
			s.setAttribute("qIndex_" + quizId, 0);
		}
		int index = (Integer) s.getAttribute("qIndex_" + quizId);
		List<Integer> qIds = quiz.getQuestionIds();
		if (qIds == null || qIds.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/quizzes");
			return;
		}
		if (index >= qIds.size()) {
			resp.sendRedirect(req.getContextPath() + "/leaderboard?quizId=" + quizId);
			return;
		}
		int qId = qIds.get(index);
		Question question = new QuestionDaoImpl().findById(qId);
		req.setAttribute("quiz", quiz);
		req.setAttribute("question", question);
		req.setAttribute("progressPercent", Math.round((index * 100.0) / qIds.size()));
		req.setAttribute("currentIndex", index + 1);
		req.setAttribute("question.options", new OptionDaoImpl().findByQuestionId(qId));
		req.getRequestDispatcher("/WEB-INF/views/user/take-quiz.jsp").forward(req, resp);
	}
}
