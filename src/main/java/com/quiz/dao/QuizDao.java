
package com.quiz.dao;

import com.quiz.model.Quiz;
import java.util.List;

public interface QuizDao {
	int create(Quiz quiz);

	void addQuestion(int quizId, int questionId, int displayOrder);

	Quiz findById(int id);

	List<Quiz> listPublished();

	void publish(int quizId, boolean published);
}
