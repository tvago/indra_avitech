package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.example.adapter.in.AddUserCommand;
import org.example.adapter.in.CommandProducerService;
import org.example.adapter.in.PrintAllUsersCommand;
import org.example.adapter.in.RemoveAllUsersCommand;
import org.example.adapter.in.ShutDownCommand;
import org.example.adapter.out.UserPersistenceAdapter;
import org.example.application.domain.model.User;
import org.example.application.domain.service.AddUserService;
import org.example.application.domain.service.CommandExecutorService;
import org.example.application.domain.service.PrintAllUsersService;
import org.example.application.domain.service.RemoveAllUsersService;
import org.example.application.port.out.UserRepository;
import org.example.configuration.DataSourceConfig;
import org.example.migration.DatabaseMigration;

public class Main {
	public static void main(String[] args) {
		//  dataSource initialization
		DataSource dataSource = DataSourceConfig.createH2DataSource();

		//  run database migrations using flyway
		DatabaseMigration.migrate(dataSource);

		//  needed repositories and services
		UserRepository userRepository = new UserPersistenceAdapter(dataSource);
		AddUserService addUserService = new AddUserService(userRepository);
		PrintAllUsersService printAllUsersService = new PrintAllUsersService(userRepository);
		RemoveAllUsersService removeAllUsersService = new RemoveAllUsersService(userRepository);


		//  producer and consumer initialization
		CommandProducerService commandProducerService = new CommandProducerService(new LinkedBlockingQueue<>());
		CommandExecutorService commandExecutor = new CommandExecutorService(commandProducerService.getQueue());
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(commandExecutor);

		//  commands inserting into queue
		commandProducerService.submit(new AddUserCommand(addUserService, new User(1, "a1", "Robert")));
		commandProducerService.submit(new AddUserCommand(addUserService, new User(2, "a2", "Martin")));
		commandProducerService.submit(new PrintAllUsersCommand(printAllUsersService));
		commandProducerService.submit(new RemoveAllUsersCommand(removeAllUsersService));
		commandProducerService.submit(new PrintAllUsersCommand(printAllUsersService));
		commandProducerService.submit(new ShutDownCommand());

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			System.out.println("All the tasks processed successfully.");
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
			System.err.println("Executor has been interrupted.");
		}
	}
}