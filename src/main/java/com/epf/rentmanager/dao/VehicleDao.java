package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository

public class VehicleDao {

	private VehicleDao() {
	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String FIND_VEHICLES_COUNT_QUERY = "SELECT COUNT(id) AS nbVehicles FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id = ?;";
	private static final String FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY = "SELECT * FROM Reservation INNER JOIN Vehicle ON Reservation.vehicle_id = Vehicle.id WHERE client_id=?;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vehicle.getConstructor());
			stmt.setInt(2, vehicle.getNumPlace());
			long key = ((PreparedStatement) stmt).executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(DELETE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, vehicle.getId());
			long key = ((PreparedStatement) stmt).executeUpdate();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long update(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vehicle.getConstructor());
			stmt.setInt(2, vehicle.getNumPlace());
			stmt.setInt(5, vehicle.getId());
			long key = stmt.executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = ((PreparedStatement) stmt).executeQuery();
			while (rs.next()) {
				Vehicle vehicule = new Vehicle(rs.getInt("id"), rs.getString("constructeur"), rs.getInt("nb_places"));
				conn.close();
				return Optional.of(vehicule);
			}

		} catch (SQLException e) {
			throw new DaoException();
		}
		return Optional.empty();
	}

	public ArrayList<Vehicle> findAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = ((PreparedStatement) stmt).executeQuery();
			ArrayList<Vehicle> vehiculeResultList = new ArrayList<Vehicle>();
			while (rs.next()) {
				Vehicle vehicle = new Vehicle(rs.getInt("id"), rs.getString("constructeur"), rs.getInt("nb_places"));
				vehiculeResultList.add(vehicle);
			}

			conn.close();
			return vehiculeResultList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public int countVehicle() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_VEHICLES_COUNT_QUERY);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt("nbVehicles");
			return count;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public ArrayList<Vehicle> findVehicleByClientId(int clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_VEHICLE_BY_CLIENT_QUERY);
			stmt.setLong(1, clientId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Vehicle> carsList = new ArrayList<Vehicle>();
			while (rs.next()) {
				Vehicle car = new Vehicle(rs.getInt("id"), rs.getString("constructeur"), rs.getByte("nb_places"));

				carsList.add(car);
			}
			conn.close();
			return carsList;
		} catch (SQLException e) {
			throw new DaoException();
		}
		
		
	}

}
