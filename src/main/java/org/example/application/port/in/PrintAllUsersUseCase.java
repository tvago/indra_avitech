package org.example.application.port.in;

import java.util.Set;

import org.example.application.domain.model.User;

public interface PrintAllUsersUseCase {
	Set<User> findAllUsers();
}
