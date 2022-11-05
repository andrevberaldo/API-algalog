package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliverInputDTO {
	
	@Valid
	@NotNull
	private CustomerIdInput customer;
	
	@Valid
	@NotNull
	private RecipientInputDTO recipient;
	
	@NotNull
	private BigDecimal fee;
}
