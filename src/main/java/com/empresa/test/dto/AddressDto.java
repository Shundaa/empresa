package com.empresa.test.dto;

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
public class AddressDto {

	@NonNull
	@NotBlank
	@Pattern(regexp = "^[0-9]{5}\\-[0-9]{3}", message="Zip code must be on format 99999-999")
	private String zipCode;
	
	@NonNull
	@NotBlank
	@Pattern(regexp = "^[0-9]+", message="the value must be positive integer")
	private String number;
	
}
