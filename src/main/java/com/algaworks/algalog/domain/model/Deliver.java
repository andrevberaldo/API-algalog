package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.algalog.domain.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Deliver {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Customer customer;
	
	@OneToMany(mappedBy = "deliver", cascade = CascadeType.ALL)
	private List<DeliverEvent> events = new ArrayList<>();
	
	@Embedded
	private Recipient recipient;
	
	private BigDecimal fee;
	
	@Enumerated(EnumType.STRING)
	private DeliverStatus status;
	
	@Column(name = "created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "concluded_at")
	private OffsetDateTime concludedAt;

	public DeliverEvent addEvent(String description) {
		DeliverEvent deliverEvent = new DeliverEvent();
		deliverEvent.setDescription(description);
		deliverEvent.setDeliver(this);
		deliverEvent.setEventDateTime(OffsetDateTime.now());
		
		this.getEvents().add(deliverEvent);
		
		return deliverEvent;
		
	}

	public void conclude() {
		if(cannotBeConcluded()) {
			throw new BusinessException("Deliver cannot be concluded");
		}
		
		this.setStatus(DeliverStatus.CONCLUDED);
		this.setConcludedAt(OffsetDateTime.now());
	}
	
	public boolean cannotBeConcluded() {
		return !status.equals(DeliverStatus.PENDING);
	}
}
