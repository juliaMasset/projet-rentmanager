package com.epf.rentmanager.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		VehicleService vehicleService = context.getBean(VehicleService.class);
		
		System.out.println(clientService);
		System.out.println(vehicleService);
		
	}
	
}