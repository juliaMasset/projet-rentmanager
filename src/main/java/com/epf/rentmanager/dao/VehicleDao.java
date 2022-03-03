package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository

public class VehicleDao {
	
	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(CREATE_VEHICLE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
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
			PreparedStatement stmt = conn.prepareStatement(DELETE_VEHICLE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, vehicle.getId());
			long key = ((PreparedStatement) stmt).executeUpdate();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		try  {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = ((PreparedStatement) stmt).executeQuery();
			conn.close();
			while (rs.next()) {
				Vehicle vehicule = new Vehicle(rs.getInt("id"), rs.getString("constructeur"), rs.getInt("nb_place"));
				System.out.println(vehicule);
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
	

}
