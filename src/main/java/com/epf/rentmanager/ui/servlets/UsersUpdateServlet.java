package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

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

@WebServlet("/users/update")

public class UsersUpdateServlet extends HttpServlet {

	@Autowired
	ClientService clientService;

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
            Optional<Client> client = clientService.findById(id);
            
            request.setAttribute("user", client.get());
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client client = new Client(Integer.parseInt(request.getParameter("id")), request.getParameter("last_name"), request.getParameter("first_name"),
				request.getParameter("email"), LocalDate.parse(request.getParameter("birthdate")));
		
		try {
			
			clientService.update(client);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("/rentmanager/users");  
		
	}
	
}
