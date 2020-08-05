package com.mercadolibre.challenge.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {

	private String error;
	private String message;
	private Object body;
	private Integer status;
}
