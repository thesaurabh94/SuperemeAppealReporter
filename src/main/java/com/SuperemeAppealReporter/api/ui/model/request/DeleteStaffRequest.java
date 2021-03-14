package com.SuperemeAppealReporter.api.ui.model.request;

import javax.validation.constraints.NotEmpty;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteStaffRequest {
	
	@NotEmpty(message = "Staff Id can not be blank")
	private String staffId;
}
