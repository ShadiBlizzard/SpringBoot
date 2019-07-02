package com.spring.springboot.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

	@Autowired
	private ModelMapper modelMapper;

	public <D, T> D map(final T ObjectToMap, Class<D> targetClass) {
		return modelMapper.map(ObjectToMap, targetClass);
	}

	public <D, T> List<D> mapAll(final Collection<T> listToMap, Class<D> targetClass) {
		return listToMap.stream().map(entity -> map(entity, targetClass)).collect(Collectors.toList());
	}

}
