package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.DeliverDTOAssembler;
import com.algaworks.algalog.api.model.DeliverDTO;
import com.algaworks.algalog.api.model.input.DeliverInputDTO;
import com.algaworks.algalog.domain.repository.DeliverRepository;
import com.algaworks.algalog.domain.service.ConcludeDeliverService;
import com.algaworks.algalog.domain.service.CreateDeliverService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivers")
public class DeliverController {

	private CreateDeliverService createDeliver;
	private DeliverRepository deliverRepository;
	private DeliverDTOAssembler deliverAssembler;
	private ConcludeDeliverService concludeDeliverService;
	
	@GetMapping
	public List<DeliverDTO> getAllDelivers() {
		return deliverAssembler.toDTOList(deliverRepository.findAll());
		
	}
	
	@GetMapping("/{deliverId}")
	public ResponseEntity<DeliverDTO> getDeliver(@PathVariable Long deliverId) {
		return deliverRepository.findById(deliverId)
				.map(deliver -> ResponseEntity.ok(deliverAssembler.toDTO(deliver)))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliverDTO create(@Valid @RequestBody DeliverInputDTO deliver) {
		var deliverEntity = deliverAssembler.toEntity(deliver);
		var dcliverCreated = createDeliver.createDemand(deliverEntity);
		return deliverAssembler.toDTO(dcliverCreated);
	}
	
	@PutMapping("/{deliverId}/conclude")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void concludeDeliver(@PathVariable Long deliverId) {
		concludeDeliverService.concludeDeliver(deliverId);
	}
}
