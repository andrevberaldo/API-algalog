package com.algaworks.algalog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
