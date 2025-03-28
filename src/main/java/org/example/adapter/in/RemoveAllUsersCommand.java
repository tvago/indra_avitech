package org.example.adapter.in;

import org.example.application.port.in.Command;
import org.example.application.port.in.RemoveAllUsersUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveAllUsersCommand implements Command {

	private RemoveAllUsersUseCase removeAllUsersUseCase;

	@Override
	public void execute() {
		removeAllUsersUseCase.removeAllUsers();
	}
}
