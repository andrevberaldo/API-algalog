package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.model.DeliverEvent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecordDeliverEvent {

	private SearchDeliverService searchDeliverService;
	
	@Transactional
	public DeliverEvent record(Long deliverId, String description) {
		Deliver deliver = searchDeliverService.getDeliver(deliverId);
		
		return deliver.addEvent(description);
	}
}
