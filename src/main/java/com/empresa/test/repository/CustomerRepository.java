package com.empresa.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.empresa.test.model.CustomerEntity;

@Component
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	CustomerEntity findByDocumentId(String documentId);
	CustomerEntity deleteByDocumentId(String documentId);
	List<CustomerEntity> findByAddressZipCode(String zipCcode);
	
}
