package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users")

public class UsersServlet extends HttpServlet {

	@Autowired
	ClientService clientService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	private static final long serialVersionUID = 1L;

	private static final String VUE_USERS = ("/WEB-INF/views/users/list.jsp");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("listUsers", this.clientService.findAll());
			getServletContext().getRequestDispatcher(VUE_USERS).forward(request, response);

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