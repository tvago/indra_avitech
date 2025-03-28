package org.example.adapter.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.example.application.domain.model.User;
import org.example.application.port.out.UserRepository;

public class UserPersistenceAdapter implements UserRepository {
	private final DataSource dataSource;

	public UserPersistenceAdapter(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addUser(User user) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)")) {
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getGuid());
			stmt.setString(3, user.getName());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to add user", e);
		}
	}

	@Override
	public Set<User> findAllUsers() {
		Set<User> users = new HashSet<>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SUSERS");
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				users.add(new User(
						rs.getInt("USER_ID"),
						rs.getString("USER_GUID"),
						rs.getString("USER_NAME")
				));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to fetch users", e);
		}
		return users;
	}

	@Override
	public void deleteAllUsers() {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM SUSERS")) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to delete users", e);
		}
	}
}
