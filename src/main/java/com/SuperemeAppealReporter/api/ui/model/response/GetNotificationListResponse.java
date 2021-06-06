package com.SuperemeAppealReporter.api.ui.model.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetNotificationListResponse {

	private String title;
	private String body;
	private String category; 
	private String docId;
	private String userEmail;
	private Long notificationTime;
}
