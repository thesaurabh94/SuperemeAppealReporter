package com.SuperemeAppealReporter.api.shared.dto;

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
public class CourtBranchDto {

	private int courtBranchId;
	private String courtBranchName;
}
