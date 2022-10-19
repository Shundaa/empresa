package com.empresa.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
	
	@NonNull
	@NotBlank
	@Pattern(regexp = "^[0-9]+", message="the value must be positive integer")
	private String documentId;
    
	@NonNull
	@NotBlank
	private String name;

	@NonNull
	@NotBlank
	@Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 150, message = "Age should not be greater than 150")
	@Pattern(regexp = "^[0-9]+", message="the value must be positive integer")
	private String age;

	@NonNull
	@NotBlank
	private String registrationDate;

	@NonNull
	@NotBlank
	private String lastUpdateInfo;

	@Valid
	private List<AddressDto> address;
	
}
