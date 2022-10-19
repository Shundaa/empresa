package com.empresa.test.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class MapperUtils {

	public static ModelMapper mapper = new ModelMapper();
	
	static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> mapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
}
