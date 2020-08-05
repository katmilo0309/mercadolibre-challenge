package com.mercadolibre.challenge.api.mapper;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.challenge.api.utils.DateUtils;

public class MapperResponseToDto {

	private static Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	@SuppressWarnings("unchecked")
	public static <T> T convert(Object object, Class<T> t) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, Object> aMap = (Map<String, Object>) object;
			return objectMapper.convertValue(aMap, objectMapper.getTypeFactory().constructType(t));
		} catch (Exception e) {
			LOG.error("converting failed! aMap: {}, class: {}", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertList(Object object, Class<T> t) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Map<String, Object>> list = (List<Map<String, Object>>) object;
			return objectMapper.convertValue(list, objectMapper.getTypeFactory().constructCollectionType(List.class, t));
		} catch (Exception e) {
			LOG.error("converting failed! aMap: {}, class: {}", e);
		}
		return null;
	}

}
