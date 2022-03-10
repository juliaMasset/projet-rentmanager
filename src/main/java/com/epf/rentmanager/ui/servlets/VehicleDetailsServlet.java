package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/cars/details")

public class VehicleDetailsServlet extends HttpServlet {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("car", vehicleService.findById(id).get());
			request.setAttribute("listResa", reservationService.findResaByVehicleId(id));
			request.setAttribute("users", clientService.findAll());

		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}

		request.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}