package com.mercadolibre.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("/itemFallBack")
	public ResponseEntity itemsFallback() {
		return new ResponseEntity<String>("Item service is taking too long to respond or is unavailable",
				HttpStatus.SERVICE_UNAVAILABLE);
	}
}
