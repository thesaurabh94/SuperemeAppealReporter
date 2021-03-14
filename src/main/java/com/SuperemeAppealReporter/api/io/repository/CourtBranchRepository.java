package com.SuperemeAppealReporter.api.io.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SuperemeAppealReporter.api.io.entity.CaseEntity;
import com.SuperemeAppealReporter.api.io.entity.CourtBranchEntity;

@Repository
public interface CourtBranchRepository extends PagingAndSortingRepository<CourtBranchEntity, Integer> {

	@Query(value = "SELECT * FROM court_branch WHERE BRANCH_NAME IN ?1 and court_entity_id = ?2",nativeQuery = true)
	List<CourtBranchEntity> getAllBranchesByName(Set<String> courtBranchEntityList, Integer courtId);
	
	@Query(value = "SELECT * FROM court_branch where is_active = 1 ",nativeQuery = true)
	Page<CourtBranchEntity> findAllActiveCourt(Pageable pageableRequest);

	
	
	@Query(value="select c from CourtBranchEntity c "+
            " where c.courtEntity.courtType like CONCAT('%',:searchValue,'%') or "+
		     " c.branchName   like CONCAT('%',:searchValue,'%') and c.active =1")
	public Page<CourtBranchEntity> findAllActiveCourtBySearch(Pageable pageable,@Param("searchValue")String searchValue);
}
