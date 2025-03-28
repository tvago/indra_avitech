package org.example.adapter.in;

import java.util.concurrent.BlockingQueue;

import org.example.application.port.in.Command;

public class CommandProducerService {
	private final BlockingQueue<Command> queue;

	public CommandProducerService(BlockingQueue<Command> queue) {
		this.queue = queue;
	}

	public void submit(Command command) {
		queue.add(command);
	}
}

