package com.SuperemeAppealReporter.api.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.SuperemeAppealReporter.api.io.entity.FirebaseUserDeviceMappingEntity;

public interface FirebaseUserDeviceMappingRepository extends PagingAndSortingRepository<FirebaseUserDeviceMappingEntity, Integer> {

	@Query(value="from FirebaseUserDeviceMappingEntity fE where fE.deviceId = :deviceId")
	public FirebaseUserDeviceMappingEntity getEntityByDeviceId(@Param("deviceId")String deviceId);
	
	@Query(value="from FirebaseUserDeviceMappingEntity fE where fE.userEntity.email = :email")
	public List<FirebaseUserDeviceMappingEntity> getEntityByUserEmail(@Param("email")String email);
	
	@Query("select deviceId from FirebaseUserDeviceMappingEntity")
	public String[] getAllDeviceId();
	
	@Query(value="select * from firebase_user_device_mapping where is_active = 1 " , nativeQuery = true)
	public List<FirebaseUserDeviceMappingEntity> getAllDeviceMappingList();
	
	
}
