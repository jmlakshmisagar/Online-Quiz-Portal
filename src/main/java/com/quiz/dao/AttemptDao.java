
package com.quiz.dao;

import com.quiz.model.Attempt;
import java.util.List;

public interface AttemptDao {
	int startAttempt(int quizId, int userId);

	void submitAttempt(int attemptId, int score);

	Attempt findById(int id);

	List<Attempt> listByQuiz(int quizId, int limit);
}
