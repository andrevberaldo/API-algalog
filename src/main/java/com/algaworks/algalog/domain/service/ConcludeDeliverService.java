package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.repository.DeliverRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ConcludeDeliverService {
	
	private DeliverRepository deliverRepository;
	private SearchDeliverService searchDeliver;

	@Transactional
	public void concludeDeliver(Long deliverId) {
		Deliver deliver = searchDeliver.getDeliver(deliverId);
		
		deliver.conclude();
		
		deliverRepository.save(deliver);	
	}
}
