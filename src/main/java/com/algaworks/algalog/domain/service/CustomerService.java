package com.algaworks.algalog.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.BusinessException;
import com.algaworks.algalog.domain.model.Customer;
import com.algaworks.algalog.domain.repository.CustomerRepositoy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {
	
	private CustomerRepositoy repository;
	
	@Transactional //declares that this method must be run in a DB transaction, if something goes wrong, rollback (database atomic).
	public Customer saveCustomer(Customer customer) {
		Optional<Customer> customerAlreadyExists = repository.findByEmail(customer.getEmail());
		boolean emailInUse = customerAlreadyExists.isPresent() && !customerAlreadyExists.get().equals(customer);
		
		if(emailInUse) {
			throw new BusinessException("Email already in use");
		}
		
		return repository.save(customer);
	}
	
	@Transactional
	public void deleteCustomerById(Long customerId){
		repository.deleteById(customerId);
	}
	
	public Customer findCustomer(Long customerId) {
		return repository.findById(customerId)
				.orElseThrow(() -> new BusinessException("Invalid Customer"));
	}
	
	 
}
