package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars")

public class VehicleServlet extends HttpServlet {

	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	private static final long serialVersionUID = 1L;

	private static final String VUE_CARS = ("/WEB-INF/views/vehicles/list.jsp");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("listVehicles", this.vehicleService.findAll());
			System.out.println(vehicleService.findAll());
			getServletContext().getRequestDispatcher(VUE_CARS).forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
