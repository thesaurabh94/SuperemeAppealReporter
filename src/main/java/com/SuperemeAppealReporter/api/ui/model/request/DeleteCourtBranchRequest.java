package com.SuperemeAppealReporter.api.ui.model.request;

import javax.validation.constraints.NotEmpty;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCourtBranchRequest {
	@NotEmpty(message = "Please provide court branch id.")
	private String courtBranchId;
}
