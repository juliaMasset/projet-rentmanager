package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/create")

public class ReservationCreateServlet extends HttpServlet {

	@Autowired
	ClientService clientService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ArrayList<Vehicle> listVehicles = vehicleService.findAll();
			ArrayList<Client> listClients = clientService.findAll();
			request.setAttribute("listCars", listVehicles);
			request.setAttribute("listUsers", listClients);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Reservation reservation = new Reservation(0, Integer.parseInt(request.getParameter("client")),
				Integer.parseInt(request.getParameter("car")), LocalDate.parse(request.getParameter("begin")),
				LocalDate.parse(request.getParameter("end")));
		try {

			reservationService.create(reservation);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("/rentmanager/rents");

	}

}
