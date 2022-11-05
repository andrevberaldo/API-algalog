package com.algaworks.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.DeliverStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliverDTO {
	
	private Long id;
	private CustomerDTO customer;
	private RecipientDTO recipient;
	private BigDecimal fee;
	private DeliverStatus status;
	private OffsetDateTime createdAt;
	private OffsetDateTime concludedAt;
}
