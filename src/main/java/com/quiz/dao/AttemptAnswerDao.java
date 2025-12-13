
package com.quiz.dao;

import com.quiz.model.AttemptAnswer;
import java.util.List;

public interface AttemptAnswerDao {
	void upsert(AttemptAnswer aa);

	List<AttemptAnswer> listByAttempt(int attemptId);
}
