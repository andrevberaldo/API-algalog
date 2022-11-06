package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.ResourceNotFoundException;
import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.repository.DeliverRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchDeliverService {
	
	private DeliverRepository deliverRepository;

	public Deliver getDeliver(Long deliverId) {
		return deliverRepository.findById(deliverId)
				.orElseThrow(() -> new ResourceNotFoundException("Deliver not found"));
	}
}
