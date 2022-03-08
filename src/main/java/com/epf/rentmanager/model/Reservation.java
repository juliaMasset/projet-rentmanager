package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {

	private int id;
	private int clientId;
	private int vehicleId;
	private LocalDate dateStart;
	private LocalDate dateEnd;

	public Reservation() {
		super();
	}

	public Reservation(int id) {
		this.id = id;
	}

	public Reservation(int id, int clientId, int vehicleId, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehiculeId) {
		this.vehicleId = vehiculeId;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

}
