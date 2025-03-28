package org.example.application.domain.service;

import org.example.application.domain.model.User;
import org.example.application.port.in.AddUserUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddUserService implements AddUserUseCase {
	@Override
	public User addUser(User user) {
		return null;
	}
}
