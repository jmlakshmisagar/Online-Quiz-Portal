
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.AttemptDaoImpl;
import com.quiz.dao.impl.AttemptAnswerDaoImpl;
import com.quiz.model.AttemptAnswer;

@WebServlet(name = "SubmitQuizServlet", urlPatterns = { "/quiz/submit" })
public class SubmitQuizServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int quizId = Integer.parseInt(req.getParameter("quizId"));
		int questionId = Integer.parseInt(req.getParameter("questionId"));
		String action = req.getParameter("action");
		HttpSession s = req.getSession();
		int attemptId = (Integer) s.getAttribute("attemptId_" + quizId);
		String sel = req.getParameter("selectedOptionId");
		Integer selectedOptionId = sel == null ? null : Integer.valueOf(sel);
		AttemptAnswer aa = new AttemptAnswer();
		aa.setAttemptId(attemptId);
		aa.setQuestionId(questionId);
		aa.setSelectedOptionId(selectedOptionId);
		aa.setCorrect(null);
		new AttemptAnswerDaoImpl().upsert(aa);
		int index = (Integer) s.getAttribute("qIndex_" + quizId);
		if ("next".equals(action)) {
			s.setAttribute("qIndex_" + quizId, index + 1);
			resp.sendRedirect(req.getContextPath() + "/quiz/take?id=" + quizId);
		} else {
			int score = 0;
			new AttemptDaoImpl().submitAttempt(attemptId, score);
			s.removeAttribute("attemptId_" + quizId);
			s.removeAttribute("qIndex_" + quizId);
			resp.sendRedirect(req.getContextPath() + "/leaderboard?quizId=" + quizId);
		}
	}
}
