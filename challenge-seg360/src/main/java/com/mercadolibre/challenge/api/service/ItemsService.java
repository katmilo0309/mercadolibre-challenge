package com.mercadolibre.challenge.api.service;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge.api.dto.ResponseDTO;

public interface ItemsService {

	ResponseEntity getItems(String id);
}
