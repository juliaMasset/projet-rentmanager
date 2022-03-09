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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/home")

public class HomeServlet extends HttpServlet{

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
	
	private static final String VUE_HOME = "/WEB-INF/views/home.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {

        try {
        	request.setAttribute("nbClients", clientService.count());
            request.setAttribute("nbVehicles", vehicleService.count());
            request.setAttribute("nbRents", reservationService.count());
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }

		getServletContext().getRequestDispatcher(VUE_HOME).forward(request, response);
		
			}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
}
