package com.SuperemeAppealReporter.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SuperemeAppealReporter.api.io.entity.UserEntity;
import com.SuperemeAppealReporter.api.io.repository.MyTableRepository;
import com.SuperemeAppealReporter.api.io.repository.UserRepository;
import com.SuperemeAppealReporter.api.service.SubscriptionPlanService;
import com.SuperemeAppealReporter.api.service.SubscriptionPlanStatusProcessor;

@Service
public class SubscriptionPlanStatusProcessorImpl implements SubscriptionPlanStatusProcessor{

	@Autowired
	MyTableRepository myTableRepository;
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired
	SubscriptionPlanService planService;
	
	@Override
	public boolean checkAndUpdatePlanStatus() {
		
		System.out.println("FIRED AT "+new Date());
		
		planService.checkAndUpdatePlanStatus();
		//myTableRepository.updateUserPlanStatus();
		return true;
	}

}
