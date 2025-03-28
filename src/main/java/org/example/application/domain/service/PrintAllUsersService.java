package org.example.application.domain.service;

import java.util.Set;

import org.example.application.domain.model.User;
import org.example.application.port.in.PrintAllUsersUseCase;
import org.example.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintAllUsersService implements PrintAllUsersUseCase {
	private UserRepository userRepository;

	@Override
	public Set<User> findAllUsers() {
		return userRepository.findAllUsers();
	}
}
