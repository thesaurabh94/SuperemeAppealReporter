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
public class SearhClientRequest {

	@NotEmpty(message = "Please provide either client name or client id.")
	private String clientNameOrId;	
	@NotEmpty(message = "Please provide client category.")
	private String clientCategory;
}
