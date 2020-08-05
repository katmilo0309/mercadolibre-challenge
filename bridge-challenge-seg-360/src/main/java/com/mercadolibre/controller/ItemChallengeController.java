package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.service.ItemServiceConsumer;

@RestController
@RequestMapping("/catalog")
public class ItemChallengeController {

	@Autowired
	private ItemServiceConsumer itemServiceConsumer;
	
	@GetMapping("/item/{id}")
	public ResponseEntity getItem(@PathVariable("id") String id) {
		return itemServiceConsumer.getItems(id);
	}
}
