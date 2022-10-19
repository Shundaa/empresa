package com.empresa.test.util;

import java.util.List;

import com.empresa.test.dto.AddressDto;
import com.empresa.test.model.AddressEntity;

public class MapperAddressUtil extends MapperUtils {

	
	public static List<AddressDto> mapperToListDto(List<AddressEntity> adressEntity) {
		return mapList(adressEntity, AddressDto.class);
	}
}
