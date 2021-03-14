package com.SuperemeAppealReporter.api.ui.model.request;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdditionalAppellantRespondentRequest {

	private String case_number;
	private String extraCaseAndYear; 	
	private String respondent;
	private String appellant;
	
}
