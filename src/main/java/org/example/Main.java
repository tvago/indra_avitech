package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.sql.DataSource;

import org.example.adapter.in.AddUserCommand;
import org.example.adapter.in.PrintAllUsersCommand;
import org.example.adapter.in.RemoveAllUsersCommand;
import org.example.adapter.out.UserPersistenceAdapter;
import org.example.application.domain.model.User;
import org.example.application.domain.service.AddUserService;
import org.example.application.domain.service.CommandExecutorService;
import org.example.application.domain.service.PrintAllUsersService;
import org.example.application.domain.service.RemoveAllUsersService;
import org.example.application.port.in.Command;
import org.example.application.port.out.UserRepository;
import org.example.configuration.DataSourceConfig;
import org.example.migration.DatabaseMigration;

public class Main {
	public static void main(String[] args) {
		// 1. Inicializácia DataSource
		DataSource dataSource = DataSourceConfig.createH2DataSource();

		// 2. Spustenie migrácií pomocou Flyway
		DatabaseMigration.migrate(dataSource);

		// 3. Vytvorenie repository a služieb
		UserRepository userRepository = new UserPersistenceAdapter(dataSource);
		AddUserService addUserService = new AddUserService(userRepository);
		PrintAllUsersService printAllUsersService = new PrintAllUsersService(userRepository);
		RemoveAllUsersService removeAllUsersService = new RemoveAllUsersService(userRepository);


		// 4. Inicializácia fronty a spracovateľov príkazov
		BlockingQueue<Command> commandQueue = new LinkedBlockingQueue<>();
		CommandExecutorService commandExecutor = new CommandExecutorService(commandQueue);
		new Thread(commandExecutor).start();

		// 5. Vloženie príkazov do fronty
		try {
			commandQueue.put(new AddUserCommand(addUserService, new User(1, "a1", "Robert")));
			commandQueue.put(new AddUserCommand(addUserService, new User(2, "a2", "Martin")));
			commandQueue.put(new PrintAllUsersCommand(printAllUsersService));
			commandQueue.put(new RemoveAllUsersCommand(removeAllUsersService));
			commandQueue.put(new PrintAllUsersCommand(printAllUsersService));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Chyba pri vkladaní príkazov do fronty: " + e.getMessage());
		}
	}
}