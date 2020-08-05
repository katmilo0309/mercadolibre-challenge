package com.mercadolibre.challenge.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.challenge.api.dto.ChildDTO;
import com.mercadolibre.challenge.api.dto.ItemDTO;
import com.mercadolibre.challenge.api.dto.ResponseDTO;
import com.mercadolibre.challenge.api.entities.Item;
import com.mercadolibre.challenge.api.mapper.MapperResponseToDto;
import com.mercadolibre.challenge.api.mapper.ObjectBuilder;
import com.mercadolibre.challenge.api.repository.ItemRepository;
import com.mercadolibre.challenge.api.service.ItemsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ItemsServiceImpl implements ItemsService {

	private static Logger LOGGER = LoggerFactory.getLogger(ItemsServiceImpl.class);

	@Value("${mercadolibre.api.base}")
	private String itemsEndpoint;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "callBackErrorResponse")
	public ResponseEntity getItems(String id) {
		ItemDTO itemResponse = getDBItem(id);
		if (itemResponse == null) {
			itemResponse = getItemFromService(id);
			Item item = ObjectBuilder.build(itemResponse);
			this.itemRepository.save(item);
		}

		return new ResponseEntity<ItemDTO>(itemResponse, HttpStatus.OK);
	}

	private List<ChildDTO> getChildrenList(String id) {
		ResponseEntity<Object> responseChildren = restTemplate.getForEntity(itemsEndpoint + id + "/children",
				Object.class);
		return MapperResponseToDto.convertList(responseChildren.getBody(), ChildDTO.class);
	}

	private ItemDTO getItemFromService(String id) {
		ResponseEntity<Object> response = restTemplate.getForEntity(itemsEndpoint + id, Object.class);
		ItemDTO item = MapperResponseToDto.convert(response.getBody(), ItemDTO.class);
		
		if (item != null) {
			item.setChildrenList(getChildrenList(id));
		}
		return item;
	}

	private ItemDTO getDBItem(String id) {
		Item item = null;
		try {
			Optional<Item> queryResponse = this.itemRepository.findById(id);
			return ObjectBuilder.build(queryResponse.get());
		} catch (Exception e) {
			return null;
		}
	}

	public ResponseEntity<ResponseDTO> callBackErrorResponse(String id, Throwable t) {
		LOGGER.error(t.getMessage());
		return new ResponseEntity<ResponseDTO>(
				ResponseDTO.builder().error(t.getClass().getSimpleName())
						.message("Error getting item " + id + "{" + t.getMessage() + "}").build(),
				HttpStatus.EXPECTATION_FAILED);
	}

}
