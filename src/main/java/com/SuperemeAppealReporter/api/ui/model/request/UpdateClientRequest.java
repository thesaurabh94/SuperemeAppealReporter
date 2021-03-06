package com.SuperemeAppealReporter.api.ui.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UpdateClientRequest {
	@NotBlank(message = "Id should not be blank")
	private String clientId;

	@NotBlank(message = "Name should not be blank")
	@Pattern(regexp = "^[A-Za-z\\s0-9]*$",message = "Name should only contains letters or numbers")
	private String name;
	
	//@NotBlank(message = "Designation should not be blank")
	private String desgination;
	
	@NotNull(message = "Please provide mobile no.")
	@Pattern(regexp = "^[1-9]{1}[0-9]{9}$",message = "Mobile Number Should not start with 0 and should be of 10 digits.")
	private String mobile;
	
	//@Digits(integer = 2,message = "Role id should be in digits",fraction = 0)
	//@NotNull(message = "Please enter role id")
	private int roleId;
	
	private int stateId;

	private int cityId;
	
	private int countryId;
	
	
	//@NotNull(message = "Zipcode should not be blank")
	//@Pattern(regexp = "^[0-9]{6}$",message = "Zipcode code should be of digits.")
	private String zipCode;
	
	@NotBlank(message = "Password should not be blank")
	private String password;
	
	private String address;
	
	private boolean userStatus;
}
