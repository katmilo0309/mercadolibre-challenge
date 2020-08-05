package com.mercadolibre.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge.api.service.ItemsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Retrieves the items information", description = "Service that consumes the Mercado libre api and stores the information in a database")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@GetMapping("/item/{id}")
	@ApiOperation(value = "Obtains the information of an item and its children and stores it in the database", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity getItems(@PathVariable("id") String id) {
		return itemsService.getItems(id);
	}
}
