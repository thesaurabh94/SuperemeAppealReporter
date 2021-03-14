package com.SuperemeAppealReporter.api.io.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SuperemeAppealReporter.api.io.entity.CourtBranchEntity;
import com.SuperemeAppealReporter.api.io.entity.CourtEntity;

public interface CourtDao {

	CourtEntity findCourtByName(String courtName);

	public void saveCourtDetails(CourtEntity courtEntity);

	Optional<CourtEntity> findCourtById(int courtId);

	List<CourtBranchEntity> findCourtBranchByName(Set<String> set, Integer id);

	public void saveCourtBranchDetails(Set<CourtBranchEntity> courtBranchEntityList);

	Optional<CourtBranchEntity> findCourtBranchById(int branchId);

	Page<CourtEntity> getCourtEntityPage(String searchValue, Pageable pageableRequest);

	Page<CourtBranchEntity> getAllCourtEntityPage(Pageable pageableRequest);
	
	
	
	

}
