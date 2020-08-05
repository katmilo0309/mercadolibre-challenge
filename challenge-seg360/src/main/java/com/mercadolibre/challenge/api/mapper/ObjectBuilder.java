package com.mercadolibre.challenge.api.mapper;

import java.util.ArrayList;

import com.mercadolibre.challenge.api.dto.ChildDTO;
import com.mercadolibre.challenge.api.dto.ItemDTO;
import com.mercadolibre.challenge.api.entities.Child;
import com.mercadolibre.challenge.api.entities.Item;
import com.mercadolibre.challenge.api.utils.DateUtils;

public class ObjectBuilder {

	public static Item build(ItemDTO itemDto) {
		Item item = Item.builder().id(itemDto.getId()).categoryId(itemDto.getCategoryId()).price(itemDto.getPrice())
				.startTime(DateUtils.getDateFromString(itemDto.getStartTime()))
				.stopTime(DateUtils.getDateFromString(itemDto.getStopTime())).title(itemDto.getTitle())
				.children(new ArrayList<>()).build();
		itemDto.getChildrenList().forEach(children -> {
			Child childItem = Child.builder().id(children.getId())
					.stopTime(DateUtils.getDateFromString(children.getStopTime())).parent(item).build();
			item.getChildren().add(childItem);
		});

		return item;
	}

	public static ItemDTO build(Item item) {
		ItemDTO itemDto = ItemDTO.builder().id(item.getId()).categoryId(item.getCategoryId()).price(item.getPrice())
				.startTime(DateUtils.getStringFromDate(item.getStartTime()))
				.stopTime(DateUtils.getStringFromDate(item.getStopTime())).title(item.getTitle())
				.childrenList(new ArrayList<>()).build();
		item.getChildren().forEach(children -> {
			itemDto.getChildrenList().add(ChildDTO.builder().id(children.getId())
					.stopTime(DateUtils.getStringFromDate(children.getStopTime())).build());
		});
		return itemDto;
	}
}
