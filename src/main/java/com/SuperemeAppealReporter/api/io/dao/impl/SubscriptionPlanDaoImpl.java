package com.SuperemeAppealReporter.api.io.dao.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.SuperemeAppealReporter.api.io.dao.SubscriptionPlanDao;
import com.SuperemeAppealReporter.api.io.entity.SubscriptionPlanEntity;
import com.SuperemeAppealReporter.api.io.entity.UserEntity;
import com.SuperemeAppealReporter.api.io.repository.SubscriptionPlanRepository;

@Component
public class SubscriptionPlanDaoImpl implements SubscriptionPlanDao{

	@Autowired SubscriptionPlanRepository subscriptionPlanRepo;
	@Override
	public void savePlan(SubscriptionPlanEntity planEntity) {
		subscriptionPlanRepo.save(planEntity);
	}
	@Override
	public Optional<SubscriptionPlanEntity> findById(int planId) {
		Optional<SubscriptionPlanEntity> planEntity = subscriptionPlanRepo.findById(planId);
		return planEntity;
	}
	
	@Transactional
	@Override
	public void deletePlan(int planId) {
		Optional<SubscriptionPlanEntity> planEntity = subscriptionPlanRepo.findById(planId);
		planEntity.get().setActive(false);
		
	}
	@Override
	public Page<SubscriptionPlanEntity> getAllSubscriptionEntityPage(Pageable pageableRequest) {
		Page<SubscriptionPlanEntity> planEntityPage = subscriptionPlanRepo.getAllSubscriptionEntityPage(pageableRequest);
		
		return planEntityPage;
	}

}
