package com.SuperemeAppealReporter.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetUserListResponse {

	private String userId;
	private String userEmail;
	private String userName;
	
}
