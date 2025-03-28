package org.example.application.domain.service;

import org.example.application.domain.model.User;
import org.example.application.port.in.AddUserUseCase;
import org.example.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddUserService implements AddUserUseCase {

	private final UserRepository userRepository;
	@Override
	public void addUser(User user) {
		 userRepository.addUser(user);
	}
}
