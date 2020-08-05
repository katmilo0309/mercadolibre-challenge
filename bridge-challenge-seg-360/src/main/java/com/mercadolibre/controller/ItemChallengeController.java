package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.service.ItemServiceConsumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/items")
@Api(value = "Retrieves the items information", description = "Service bridge between api gateway and challenge-seg360")
public class ItemChallengeController {

	@Autowired
	private ItemServiceConsumer itemServiceConsumer;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Obtains the information of an item provided by challenge-seg360", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity getItem(@PathVariable("id") String id) {
		return itemServiceConsumer.getItems(id);
	}
}
