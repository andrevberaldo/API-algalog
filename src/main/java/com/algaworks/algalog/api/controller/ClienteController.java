package com.algaworks.algalog.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Customer;

@RestController
public class ClienteController {
	
	@GetMapping("/customers")
	public List<Customer> list() {
		var c1 = new Customer();
		c1.setId(1L);
		c1.setName("André");
		c1.setEmail("andreberaldo@hotmail.com");
		c1.setPhone("19 98201-2832");
		
		var c2 = new Customer();
		c2.setId(2L);
		c2.setName("Juliana");
		c2.setEmail("Julianaberaldo@hotmail.com");
		c2.setPhone("");
		
			
		return Arrays.asList(c1, c2);
		
	}

}
