
package com.quiz.dao;

import com.quiz.model.Question;
import java.util.List;

public interface QuestionDao {
	int create(Question q);

	Question findById(int id);

	List<Question> listAll(int offset, int limit);
}
