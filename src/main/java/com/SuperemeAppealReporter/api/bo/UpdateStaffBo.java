package com.SuperemeAppealReporter.api.bo;

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
public class UpdateStaffBo {

	private String staffId;
	
	private String name;

	private String password;

	private String desgination;
	
	private String mobile;
	
	private int roleId;
	
	private int stateId;
	
	private String zipCode;
	
	private int cityId;
	
	private int countryId;
	
	private String address;
}
