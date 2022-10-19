package com.empresa.test.util;

import java.util.List;

import com.empresa.test.dto.CustomerDto;
import com.empresa.test.model.CustomerEntity;

public class MapperCustomerUtil extends MapperUtils {
	
	public static List<CustomerDto> mapperToListDto(List<CustomerEntity> custumerEntity) {
		return mapList(custumerEntity, CustomerDto.class);
	}
	
	public static List<CustomerEntity> mapperToListEntity(List<CustomerDto> custumerDto) {
		return mapList(custumerDto, CustomerEntity.class);
	}
	
	public static CustomerDto mapperToDto(CustomerEntity custumerEntity) {
		CustomerDto customerDto = new CustomerDto();
		mapper.map(custumerEntity, customerDto);
		return customerDto;
	}
	
	public static CustomerEntity mapperToEntity(CustomerDto custumerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		mapper.map(custumerDto, customerEntity);
		return customerEntity;
	}
	
}
