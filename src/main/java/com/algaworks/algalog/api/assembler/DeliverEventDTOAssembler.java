package com.algaworks.algalog.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.DeliverEventDTO;
import com.algaworks.algalog.domain.model.DeliverEvent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliverEventDTOAssembler {
	
	private ModelMapper modelMapper;
	
	public DeliverEventDTO toDTO(DeliverEvent deliverEvent) {
		return modelMapper.map(deliverEvent, DeliverEventDTO.class);
	}
	
	public List<DeliverEventDTO> toDTOList(List<DeliverEvent> deliverEvents){
		List<DeliverEventDTO> list = new ArrayList<>();		
		
		deliverEvents.forEach(event -> list.add(toDTO(event)));
		
		return list;
	}
}
