
package com.quiz.dao;

import com.quiz.model.User;

public interface UserDao {
	User findByUsername(String username);

	User findById(int id);

	int create(User user);
}
