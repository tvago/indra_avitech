package org.example.migration;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {
	public static void migrate() {
		Flyway flyway = Flyway.configure()
							  .dataSource("jdbc:h2:mem:testdb", "sa", "")
							  .load();
		flyway.migrate();
	}
}