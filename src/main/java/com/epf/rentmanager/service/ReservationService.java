package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

@Service

public class ReservationService {

	private ReservationDao reservationDao;

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public long create(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.create(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.delete(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Reservation> findResaByClientId(int clientId) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reservation> findResaByVehicleId(int vehicleId) throws ServiceException {
		try {
			return this.reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws ServiceException {
		try {
			return this.reservationDao.countReservation();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
