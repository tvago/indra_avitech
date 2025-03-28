package org.example.application.domain.service;

import java.util.concurrent.BlockingQueue;

import org.example.adapter.in.ShutDownCommand;
import org.example.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandExecutorService implements Runnable {
	private final BlockingQueue<Command> queue;

	@Override
	public void run() {
		while (true) {
			try {
				Command cmd = queue.take();
				if(cmd instanceof ShutDownCommand){
					cmd.execute();
					break;
				}
				cmd.execute();
			} catch (InterruptedException e) {
				break; // clean shutdown
			}
		}
	}
}
