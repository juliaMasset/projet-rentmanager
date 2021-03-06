package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository

public class ClientDao {

	private ClientDao() {
	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String FIND_CLIENTS_COUNT_QUERY = "SELECT COUNT(id) AS nbClients FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id = ?;";
	private static final String FIND_RESERVATIONS_CLIENT_BY_VEHICLE_QUERY = "SELECT * FROM Reservation INNER JOIN Client ON Reservation.client_id = Client.id WHERE vehicle_id=?;";

	public long create(Client client) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			(stmt).setString(1, client.getLastname());
			(stmt).setString(2, client.getFirstname());
			(stmt).setString(3, client.getEmail());
			(stmt).setDate(4, Date.valueOf(client.getBirthdate()));
			long key = stmt.executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int delete(Client client) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, client.getId());
			int key = pstmt.executeUpdate();
			conn.close();
			if (conn.isClosed())
				System.out.println("Connection closed.");
			return key;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public long update(Client client) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, client.getFirstname());
			stmt.setString(2, client.getLastname());
			stmt.setString(3, client.getEmail());
			stmt.setDate(4, Date.valueOf(client.getBirthdate()));
			stmt.setInt(5, client.getId());
			long key = stmt.executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Optional<Client> findById(int id) throws DaoException {

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int clientId = rs.getInt("id");
			String clientLastname = rs.getString("nom");
			String clientFirstname = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();

			Client client = new Client(clientId, clientLastname, clientFirstname, clientEmail, clientBirthdate);

			return Optional.of(client);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException();
		}

	}

	public ArrayList<Client> findAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_CLIENTS_QUERY);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Client> clientResultList = new ArrayList<Client>();
			while (rs.next()) {
				Client client = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getDate("naissance").toLocalDate());
				System.out.println(client);
				clientResultList.add(client);
			}

			conn.close();
			return clientResultList;

		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	public int countClients() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_CLIENTS_COUNT_QUERY);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt("nbClients");
			return count;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public ArrayList<Client> findClientByVehicleId(int vehicleId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_CLIENT_BY_VEHICLE_QUERY);
			stmt.setLong(1, vehicleId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Client> clientList = new ArrayList<Client>();
			while (rs.next()) {
				Client client = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getDate("naissance").toLocalDate());

				clientList.add(client);
			}
			conn.close();
			return clientList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
