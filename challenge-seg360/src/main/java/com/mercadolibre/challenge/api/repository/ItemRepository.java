package com.mercadolibre.challenge.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.mercadolibre.challenge.api.entities.Item;

public interface ItemRepository extends CrudRepository<Item, String>{

}
