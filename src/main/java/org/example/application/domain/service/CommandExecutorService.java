package org.example.application.domain.service;

import java.util.concurrent.BlockingQueue;

import org.example.application.port.in.Command;

public class CommandExecutorService implements Runnable {
	private final BlockingQueue<Command> queue;

	public CommandExecutorService(BlockingQueue<Command> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Command cmd = queue.take();
				cmd.execute();
			} catch (InterruptedException e) {
				break; // clean shutdown
			}
		}
	}
}
