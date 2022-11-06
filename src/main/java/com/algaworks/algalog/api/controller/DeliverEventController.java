package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.DeliverEventDTOAssembler;
import com.algaworks.algalog.api.model.DeliverEventDTO;
import com.algaworks.algalog.api.model.input.DeliverEventInputDTO;
import com.algaworks.algalog.domain.model.DeliverEvent;
import com.algaworks.algalog.domain.service.RecordDeliverEvent;
import com.algaworks.algalog.domain.service.SearchDeliverService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivers/{deliverId}/events")
public class DeliverEventController {
	
	private RecordDeliverEvent recordDeliverEventService;
	private DeliverEventDTOAssembler deliverEventAssembler;
	private SearchDeliverService searchDeliverService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliverEventDTO addEvent(@PathVariable Long deliverId, @Valid @RequestBody DeliverEventInputDTO eventDescription) {
		DeliverEvent recordedEvent = recordDeliverEventService
				.record(deliverId, eventDescription.getDescription());
		
		return deliverEventAssembler.toDTO(recordedEvent);
	}
	
	@GetMapping
	public List<DeliverEventDTO> findAllDeliverEvents(@PathVariable Long deliverId){
		List<DeliverEvent> events = searchDeliverService.getDeliver(deliverId).getEvents();
		
		return deliverEventAssembler.toDTOList(events);
	}

}
