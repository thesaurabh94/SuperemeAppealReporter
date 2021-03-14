package com.SuperemeAppealReporter.api.io.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.SuperemeAppealReporter.api.io.dao.AdminDao;
import com.SuperemeAppealReporter.api.io.entity.UserEntity;
import com.SuperemeAppealReporter.api.io.repository.UserRepository;

@Component
public class AdminDaoImpl implements AdminDao {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Page<UserEntity> getUserEntityPageByUserType(Pageable pageableRequest,String userType) {
		
		Page<UserEntity> userEntityPage = userRepository.getUserEntityPageByUserType(userType,pageableRequest);
	
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> getActiveUserEntityPageByUserType(Pageable pageableRequest, String userType) {
		// TODO Auto-generated method stub
		Page<UserEntity> userEntityPage = userRepository.getActiveUserEntityPageByUserType(userType,pageableRequest);
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> getInActiveUserEntityPageByUserType(Pageable pageableRequest, String userType) {
		// TODO Auto-generated method stub
		Page<UserEntity> userEntityPage = userRepository.getInActiveUserEntityPageByUserType(userType,pageableRequest);
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> getUserEntityPageForAllStaff(List<String> staffType, Pageable pageableRequest) {
		Page<UserEntity> userEntityPage = userRepository.getUserEntityPageForAllStaff(staffType, pageableRequest);
		
		return userEntityPage;
	}

	@Override
	public Optional<UserEntity> findStaffById(int staffId) {
		Optional<UserEntity> userEntity = userRepository.findById(staffId);
		return userEntity;
	}
	
	@Transactional
	@Override
	public void deleteStaffById(Integer id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		userEntity.get().setActive(false);
	}

	@Transactional
	@Override
	public void deleteClientById(Integer id) {
		
		Optional<UserEntity> userEntity = userRepository.findById(id);
		userEntity.get().setActive(false);
	}

	@Override
	public Page<UserEntity> getUserEntityPageByUserTypeAndSubscriptionTypeAndByClientNameOrId(Pageable pageableRequest,
			String userType, String clientNameOrId, List<Integer> subsriptionTypeList) {
		Page<UserEntity> userEntityPage = userRepository.getUserEntityPageByUserTypeAndSubscriptionTypeAndByClientNameOrId(userType,clientNameOrId,subsriptionTypeList,pageableRequest);
		
		return userEntityPage;
	}

	@Override
	public Page<UserEntity> getUserEntityPageByUserTypeAndByStaffNameOrId(Pageable pageableRequest,
			String staffNameOrId, List<String> userTypeList) {
		Page<UserEntity> userEntityPage = userRepository.getUserEntityPageByUserTypeAndSubscriptionTypeAndByClientNameOrId(staffNameOrId,userTypeList,pageableRequest);
		
		return userEntityPage;
	}


}
