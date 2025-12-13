
package com.quiz.web.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.quiz.dao.impl.AttemptDaoImpl;
import com.quiz.dao.impl.QuizDaoImpl;

@WebServlet(name = "LeaderboardServlet", urlPatterns = { "/leaderboard" })
public class LeaderboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int quizId = Integer.parseInt(req.getParameter("quizId"));
		req.setAttribute("quiz", new QuizDaoImpl().findById(quizId));
		req.setAttribute("leaders", new AttemptDaoImpl().listByQuiz(quizId, 50));
		req.getRequestDispatcher("/WEB-INF/views/user/leaderboard.jsp").forward(req, resp);
	}
}
