package com.algaworks.algalog.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.model.DeliverStatus;
import com.algaworks.algalog.domain.repository.DeliverRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateDeliverService {
	
	private DeliverRepository deliverRepository;
	private CustomerService customerService;
	
	@Transactional
	public Deliver createDemand(Deliver deliver) {
		var customerID = deliver.getCustomer().getId();
		
		var customer = customerService.findCustomer(customerID);

		deliver.setCustomer(customer);
		deliver.setStatus(DeliverStatus.PENDING);
		deliver.setCreatedAt(LocalDateTime.now());
		
		
		return deliverRepository.save(deliver);
	}
}
