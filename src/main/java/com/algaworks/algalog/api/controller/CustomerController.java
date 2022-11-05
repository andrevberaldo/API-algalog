package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Customer;
import com.algaworks.algalog.domain.repository.CustomerRepositoy;
import com.algaworks.algalog.domain.service.CustomerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor //Instead of use @Autowired on CustomerRepository this constructor will turn us able to ease testing the code using dependency injection 
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerRepositoy repository;
	private CustomerService service;
	
	@GetMapping
	public List<Customer> list() {
		return repository.findAll();
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
		
		return repository.findById(customerId)
				.map(ResponseEntity::ok) // method reference passing each customer on the lambda (customer -> ResponseEntity.ok(customer))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@Valid @RequestBody Customer customer){
		return service.saveCustomer(customer);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,@Valid @RequestBody Customer updatedCustomer) {
		if(!repository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		updatedCustomer.setId(customerId);
		updatedCustomer = service.saveCustomer(updatedCustomer);
		
		return ResponseEntity.ok(updatedCustomer);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId){
		if(!repository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}
		
		service.deleteCustomerById(customerId);
		return ResponseEntity.noContent().build();
	}
	

}
