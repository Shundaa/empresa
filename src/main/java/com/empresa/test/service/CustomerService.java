package com.empresa.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empresa.test.dto.CustomerDto;
import com.empresa.test.model.CustomerEntity;
import com.empresa.test.repository.CustomerRepository;
import com.empresa.test.util.MapperCustomerUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	private static final String LOGSTART = "Start customerService: ";
	private static final String LOGFINISH = "Start customerService: ";
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDto registerNewCustomer(CustomerDto customerDto) {
		log.info(LOGSTART+"registerNewCustomer " +customerDto.getDocumentId());
		if (customerRepository.findByDocumentId(customerDto.getDocumentId()) == null) {
			CustomerEntity customerEntity = customerRepository.save(MapperCustomerUtil.mapperToEntity(customerDto));
			log.info(LOGFINISH+"registerNewCustomer " +customerDto.getDocumentId());
			return MapperCustomerUtil.mapperToDto(customerEntity);
		} else
			log.info(LOGFINISH+"registerNewCustomer " +customerDto.getDocumentId());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already registred");
	}

	public CustomerDto findCustomerByDocumentId(String documentId) {
		log.info(LOGSTART+"findCustomerByDocumentId " +documentId);
		CustomerEntity customerEntity = customerRepository.findByDocumentId(documentId);
		if ( customerEntity != null) {
			log.info(LOGFINISH+"findCustomerByDocumentId " +documentId);
			return MapperCustomerUtil.mapperToDto(customerEntity);
		}
		else {
			log.info(LOGFINISH+"findCustomerByDocumentId " +documentId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not registred");
		}
	}
	
	public Object deleteByDocumentId(String documentId) {
		log.info(LOGSTART+"deleteByDocumentId " +documentId);
		CustomerEntity customerEntity = customerRepository.findByDocumentId(documentId);
		if ( customerEntity != null) {
			customerRepository.delete(customerEntity);
			log.info(LOGFINISH+"deleteByDocumentId " +documentId);
			return null;
		}
		else {
			log.info(LOGFINISH+"deleteByDocumentId " +documentId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not registred");
		}
	}

	public CustomerDto updateCustomer(CustomerDto customerDto) {
		log.info(LOGSTART+"updateCustomer " +customerDto.getDocumentId());
		if (customerRepository.findByDocumentId(customerDto.getDocumentId()) != null) {
			CustomerEntity customerEntity = customerRepository.save(MapperCustomerUtil.mapperToEntity(customerDto));
			log.info(LOGFINISH+"updateCustomer " +customerDto.getDocumentId());
			return MapperCustomerUtil.mapperToDto(customerEntity);
		}
		else {
			log.info(LOGFINISH+"updateCustomer " +customerDto.getDocumentId());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not registred");
		}
	}

	public List<CustomerDto> findAllCustomer() {
		log.info(LOGSTART+"findAllCustomer");
		List<CustomerEntity> custumerEntity = (List<CustomerEntity>) customerRepository.findAll();
		if(custumerEntity !=null && !custumerEntity.isEmpty()) {
			log.info(LOGFINISH+"findAllCustomer");
			return MapperCustomerUtil.mapperToListDto(custumerEntity);
		}
		else {
			log.info(LOGFINISH+"findAllCustomer");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non Customer Registered");
		}
	}
	
	public List<CustomerDto> findCustomerByZipCode(String zipCcode) {
		log.info(LOGSTART+"findCustomerByZipCode " +zipCcode);
		List<CustomerEntity> custumerEntity = customerRepository.findByAddressZipCode(zipCcode);
		if (custumerEntity != null && !custumerEntity.isEmpty()) {
			log.info(LOGFINISH+"findCustomerByZipCode " +zipCcode);
			return MapperCustomerUtil.mapperToListDto(custumerEntity);
		}
		else {
			log.info(LOGFINISH+"findCustomerByZipCode " +zipCcode);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not registred");
		}
	}
	

}
