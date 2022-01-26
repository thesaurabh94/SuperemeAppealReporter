package com.SuperemeAppealReporter.api.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.SuperemeAppealReporter.api.io.entity.FirebaseUserNotificationEntity;
@Repository
public interface FirebaseUserNotificationRepository extends PagingAndSortingRepository<FirebaseUserNotificationEntity, Integer> {

	@Query(value = "select * from firebase_user_notification where status = 200 and firebase_user_device_mapping_entity_id = ?1 and is_active =1 order by created_date desc limit 30", nativeQuery = true)
	List<FirebaseUserNotificationEntity> findByDeviceIdAndStatus(Integer deviceId);

	 
}
