package org.example.adapter.in;

import org.example.application.port.in.Command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShutDownCommand implements Command {
	@Override
	public void execute() {
		System.out.println("Shutdown command received to break the loop logic");
	}
}
