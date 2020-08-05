package com.mercadolibre.challenge.api.utils;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.Getter;

@Getter
public class ItemServiceErrorHandler implements ResponseErrorHandler {

	private Integer status;
	private String error;

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return new DefaultResponseErrorHandler().hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		status = response.getRawStatusCode();
		error = response.getStatusCode().getReasonPhrase();
	}
}
