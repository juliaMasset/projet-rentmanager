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
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository

public class ReservationDao {

	private ReservationDao() {
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATIONS_COUNT_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";

	public long create(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, reservation.getClientId());
			stmt.setInt(2, reservation.getVehicleId());
			stmt.setDate(3, Date.valueOf(reservation.getDateStart()));
			stmt.setDate(4, Date.valueOf(reservation.getDateEnd()));
			long key = ((PreparedStatement) stmt).executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(DELETE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, reservation.getClientId());
			long key = ((PreparedStatement) stmt).executeUpdate();
			conn.close();
			return key;
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	public ArrayList<Reservation> findResaByClientId(int clientId) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			stmt.setLong(1, clientId);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Reservation> resaList = new ArrayList<Reservation>();
			while (rs.next()) {
				Reservation resa = new Reservation(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("vehicle_id"),
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());
				resaList.add(resa);
			}
			conn.close();
			return resaList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public ArrayList<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {

		try {
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			stmt.setLong(1, vehicleId);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Reservation> resaList = new ArrayList<Reservation>();
			while (rs.next()) {
				Reservation resa = new Reservation(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("vehicle_id"),
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());
				resaList.add(resa);
			}
			
			conn.close();
			return resaList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public ArrayList<Reservation> findAll() throws DaoException {

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Reservation> resaAllList = new ArrayList<Reservation>();
			while (rs.next()) {
				Reservation resa = new Reservation(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("vehicle_id"),
						rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());
				resaAllList.add(resa);
			}

			conn.close();
			return resaAllList;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int countReservation() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(FIND_RESERVATIONS_COUNT_QUERY);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			return count;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
