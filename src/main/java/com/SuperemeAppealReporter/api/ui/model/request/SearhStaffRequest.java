package com.SuperemeAppealReporter.api.ui.model.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearhStaffRequest {

	@NotEmpty(message = "Please provide either staff name or staff id.")
	private String staffNameOrId;
	@NotEmpty(message = "Please provide staff category.")
	private String staffCategory;

}
