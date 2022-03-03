package com.epf.rentmanager.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ClientTest {

	@Test
	
	public void testClient(){
		Client client = new Client(1, "jean", "paul", "jdhz@epfedu.fr", LocalDate.of(2012, 05, 05));
		
		assertEquals(client.getFirstname(), "jean");
	}
	
}
