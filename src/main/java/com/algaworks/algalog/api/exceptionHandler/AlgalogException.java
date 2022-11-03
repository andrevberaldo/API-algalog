package com.algaworks.algalog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class AlgalogException {
	private Integer status;
	private LocalDateTime datetime;
	private String title;
	private List<Field> fields;
	
	
	
	@AllArgsConstructor
	@Getter
	public static class Field {
		private String fieldName;
		private String message;
	}
}
