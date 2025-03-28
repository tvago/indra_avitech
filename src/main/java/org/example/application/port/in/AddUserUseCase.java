package org.example.application.port.in;

import org.example.application.domain.model.User;

public interface AddUserUseCase {
	User addUser(User user);
}
