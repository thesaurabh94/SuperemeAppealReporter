package com.SuperemeAppealReporter.api.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SuperemeAppealReporter.api.io.entity.CaseTopicEntity;

@Repository
public interface CaseTopicRepository extends JpaRepository<CaseTopicEntity, Integer>{

	@Query(value = "select  h.topic from CaseTopicEntity h where upper(h.topic) like %:topic% and h.caseEntity.isLive=1 ", countQuery = " select count(h.topic) from CaseTopicEntity h where h.caseEntity.isLive=1")
	Page<String> searchTopic(@Param("topic") String topic, Pageable pageableRequest);

}
