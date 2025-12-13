
package com.quiz.dao;

import com.quiz.model.Option;
import java.util.List;

public interface OptionDao {
	int create(Option o);

	List<Option> findByQuestionId(int questionId);
}
