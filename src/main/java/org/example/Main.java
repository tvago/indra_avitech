package org.example;

import javax.sql.DataSource;

import org.example.adapter.out.UserPersistenceAdapter;
import org.example.application.domain.service.AddUserService;
import org.example.application.port.in.AddUserUseCase;
import org.example.application.port.out.UserRepository;
import org.example.configuration.DataSourceConfig;
import org.example.migration.DatabaseMigration;

public class Main {
	public static void main(String[] args) {
		DataSource dataSource = DataSourceConfig.createH2DataSource();
		DatabaseMigration.migrate();

		UserRepository userRepository = new UserPersistenceAdapter(dataSource);
		AddUserUseCase addUserUseCase = new AddUserService();
	}
}