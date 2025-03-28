package org.example.application.domain.service;

import java.util.Set;

import org.example.adapter.out.UserPersistenceAdapter;
import org.example.application.domain.model.User;
import org.example.application.port.in.PrintAllUsersUseCase;

public class PrintAllUsersService implements PrintAllUsersUseCase {
	private UserPersistenceAdapter userPersistenceAdapter;

	@Override
	public Set<User> findAllUsers() {
		return userPersistenceAdapter.findAllUsers();
	}
}
