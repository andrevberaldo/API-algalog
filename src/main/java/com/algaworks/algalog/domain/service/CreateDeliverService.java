package com.algaworks.algalog.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.BusinessException;
import com.algaworks.algalog.domain.model.Customer;
import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.model.DeliverStatus;
import com.algaworks.algalog.domain.repository.CustomerRepositoy;
import com.algaworks.algalog.domain.repository.DeliverRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateDeliverService {
	
	private CustomerRepositoy customerRepository;
	private DeliverRepository deliverRepository;
	
	@Transactional
	public Deliver createDemand(Deliver deliver) {
		var customerID = deliver.getCustomer().getId();
		
		Customer customer = customerRepository.findById(customerID)
				.orElseThrow(() -> new BusinessException("Invalid Customer"));

		deliver.setCustomer(customer);
		deliver.setStatus(DeliverStatus.PENDING);
		deliver.setCreatedAt(LocalDateTime.now());
		
		
		return deliverRepository.save(deliver);
	}
}
