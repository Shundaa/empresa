package com.empresa.test.controller.v1;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.test.dto.CustomerDto;
import com.empresa.test.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/customer")
@ApiOperation(value = "Customers")
@Slf4j
public class CustomerController {

	private static final String LOGSTART = "Start CustomerController: ";
	
	@Autowired
	private CustomerService customerService;

	
	@PostMapping("/register")
	@ApiOperation(value = "Register a new Customer and his adresses")
	public CustomerDto register(@Valid @RequestBody CustomerDto customerDto) {
		log.info(LOGSTART+"register " +customerDto.getDocumentId());
		return customerService.registerNewCustomer(customerDto);
	}
	
	@GetMapping("/")
	@ApiOperation(value = "List of all Customer registred")
	public List<CustomerDto> listRegisters() {
		log.info(LOGSTART+"listRegisters ");
		return customerService.findAllCustomer();
	}

	@GetMapping("/{documentId}")
	@ApiOperation(value = "Find a Customer by his document Id")
	public CustomerDto findByDocumentId(@PathVariable String documentId) {
		log.info(LOGSTART+"findByDocumentId "+documentId);
		return customerService.findCustomerByDocumentId(documentId);
	}
	
	@GetMapping("/address/{zipCode}")
	@ApiOperation(value = "Find all Customers by zip code")
	public List<CustomerDto> findByzipCode(@PathVariable String zipCode) {
		log.info(LOGSTART+"findByzipCode "+zipCode);
		return customerService.findCustomerByZipCode(zipCode);
	}
	
	@DeleteMapping("/{documentId}")
	@ApiOperation(value = "Delete a Customer by his document Id")
	public Object deleteByDocumentId(@PathVariable String documentId) {
		log.info(LOGSTART+"deleteByDocumentId "+documentId);
		return customerService.deleteByDocumentId(documentId);
	}
	
	@PutMapping("/register")
	@ApiOperation(value = "Edit a Customer and/or Address")
	public CustomerDto putByDocumentId(@Valid @RequestBody CustomerDto customerDto) {
		log.info(LOGSTART+"putByDocumentId "+customerDto.getDocumentId());
		return customerService.updateCustomer(customerDto);
	}
	
}
