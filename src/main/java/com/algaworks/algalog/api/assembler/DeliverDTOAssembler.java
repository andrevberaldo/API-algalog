package com.algaworks.algalog.api.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.DeliverDTO;
import com.algaworks.algalog.domain.model.Deliver;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliverDTOAssembler {
	
	private ModelMapper modelMapper;
	
	public DeliverDTO toDTO(Deliver deliver) {
		return modelMapper.map(deliver, DeliverDTO.class);
	}
	
	public List<DeliverDTO> toDTOList(List<Deliver> deliveries) {
		List<DeliverDTO> deliveriesList = new ArrayList<>();
		deliveries.forEach(deliver -> deliveriesList.add(toDTO(deliver)));
		return deliveriesList;
	}
}
