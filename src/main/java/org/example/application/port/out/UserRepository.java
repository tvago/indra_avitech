package org.example.application.port.out;

import java.util.Set;

import org.example.application.domain.model.User;

public interface UserRepository {
	void addUser(User user);
	Set<User> findAllUsers();
	void deleteAllUsers();
}