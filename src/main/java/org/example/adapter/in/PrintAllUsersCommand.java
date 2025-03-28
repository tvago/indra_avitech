package org.example.adapter.in;

import java.util.Set;

import org.example.application.domain.model.User;
import org.example.application.port.in.Command;
import org.example.application.port.in.PrintAllUsersUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrintAllUsersCommand implements Command {

	private PrintAllUsersUseCase printAllUsersUseCase;

	@Override
	public void execute() {
		Set<User> allUsers = printAllUsersUseCase.findAllUsers();
		allUsers.forEach(System.out::println);
	}
}
