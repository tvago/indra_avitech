package org.example.adapter.in;

import org.example.application.domain.model.User;
import org.example.application.port.in.AddUserUseCase;
import org.example.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserCommand implements Command {

	private AddUserUseCase addUserUseCase;
	private User user;

	@Override
	public void execute() {
		addUserUseCase.addUser(user);
	}
}
