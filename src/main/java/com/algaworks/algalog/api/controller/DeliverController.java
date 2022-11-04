package com.algaworks.algalog.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Deliver;
import com.algaworks.algalog.domain.service.CreateDeliverService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/delivers")
public class DeliverController {

	private CreateDeliverService createDeliver;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Deliver create(@RequestBody Deliver deliver) {
		return createDeliver.createDemand(deliver);
	}
}
