package com.SuperemeAppealReporter.api.shared.dto;

import com.SuperemeAppealReporter.api.enums.SubscriptionType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlanListDto {

	private int id;
	private SubscriptionType subscriptionType;
	private String subscriptionName;
	private double subscriptionCost;
	private String subscriptionDescription;
	private String subscriptionParellelUserCount;
}
