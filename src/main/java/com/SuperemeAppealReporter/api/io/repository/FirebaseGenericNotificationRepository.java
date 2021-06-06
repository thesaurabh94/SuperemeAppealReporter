package com.SuperemeAppealReporter.api.io.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.SuperemeAppealReporter.api.io.entity.FirebaseGenericNotificationEntity;

@Repository
public interface FirebaseGenericNotificationRepository extends PagingAndSortingRepository<FirebaseGenericNotificationEntity, Integer> {

	@Query(value = "select * from firebase_generic_notification where is_active = 1 and status = 200 order by created_date desc ", nativeQuery = true)
	List<FirebaseGenericNotificationEntity> findNotificationsBetweenDates();

	
	
}

