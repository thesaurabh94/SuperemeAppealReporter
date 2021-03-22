package com.SuperemeAppealReporter.api.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.SuperemeAppealReporter.api.io.entity.CitationCategoryEntity;

public interface CitationCategoryRepository extends PagingAndSortingRepository<CitationCategoryEntity, Integer>{

	@Query(value = "select * from citation_category where is_active =1", nativeQuery = true)
	List<CitationCategoryEntity> findAllActive();

}
