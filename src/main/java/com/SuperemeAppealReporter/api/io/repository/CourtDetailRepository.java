package com.SuperemeAppealReporter.api.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.SuperemeAppealReporter.api.io.entity.CourtDetailEntity;

@Repository
public interface CourtDetailRepository extends PagingAndSortingRepository<CourtDetailEntity, Integer>{

}
