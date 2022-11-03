package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Customer;

@Repository
public interface CustomerRepositoy extends JpaRepository<Customer, Long>{
	
	// the name of the methods are recognized by spring data jpa and it implements the queries. 
	List<Customer> findByName(String name);
	
	// When use "Containing" on the method name, SDJPA use 'like' on the sql.
	List<Customer> findByNameContaining(String name);
	
	Optional<Customer> findByEmail(String email);
}
