package com.SuperemeAppealReporter.api.bo;

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
public class SearchStaffBo {
	
	
	private String staffNameOrId;
	private String staffCategory;
}
