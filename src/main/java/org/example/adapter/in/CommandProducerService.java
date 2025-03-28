package org.example.adapter.in;

import java.util.concurrent.BlockingQueue;

import org.example.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandProducerService {
	private final BlockingQueue<Command> queue;

	public void submit(Command command) {
		queue.add(command);
	}
}

