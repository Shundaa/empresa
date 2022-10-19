package com.empresa.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.test.model.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	List<AddressEntity> findByZipCode(String zipCode);

}
