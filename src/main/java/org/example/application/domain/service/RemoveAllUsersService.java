package org.example.application.domain.service;

import org.example.application.port.in.RemoveAllUsersUseCase;
import org.example.application.port.out.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveAllUsersService implements RemoveAllUsersUseCase {

	private UserRepository userRepository;

	@Override
	public void removeAllUsers() {
		userRepository.deleteAllUsers();
	}
}
