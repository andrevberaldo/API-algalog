package com.algaworks.algalog.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientDTO {
	
	private String name;
	private String address;
	private String number;
	private String cityStateAndZipCode;	 
}
