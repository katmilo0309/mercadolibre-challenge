package com.mercadolibre.service;

import org.springframework.http.ResponseEntity;

public interface ItemServiceConsumer {

	ResponseEntity getItems(String id);
}
