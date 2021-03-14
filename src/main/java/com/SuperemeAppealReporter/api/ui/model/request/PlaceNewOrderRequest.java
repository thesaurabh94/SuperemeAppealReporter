package com.SuperemeAppealReporter.api.ui.model.request;


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
public class PlaceNewOrderRequest {

	private int planId;
	private String useremail;
	
}
