package com.SuperemeAppealReporter.api.io.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.SuperemeAppealReporter.api.io.dao.CourtDao;
import com.SuperemeAppealReporter.api.io.entity.CourtBranchEntity;
import com.SuperemeAppealReporter.api.io.entity.CourtEntity;
import com.SuperemeAppealReporter.api.io.repository.CourtBranchRepository;
import com.SuperemeAppealReporter.api.io.repository.CourtRepository;

@Component
public class CourtDaoImpl implements CourtDao{

	@Autowired CourtRepository courtRepo;
	
	@Autowired CourtBranchRepository branchRepo;
	
	@Override
	public CourtEntity findCourtByName(String courtName) {
		Optional<CourtEntity> courtEntity = courtRepo.findByName(courtName);
		
		return null;
	}
	@Transactional
	@Override
	public void saveCourtDetails(CourtEntity courtEntity) {
		CourtEntity cEntity = courtRepo.save(courtEntity);
		
		Set<CourtBranchEntity> branchEntity = new HashSet<>();
		branchEntity.addAll(cEntity.getCourtBranchSet());
		
		for(CourtBranchEntity branch : branchEntity){
			branch.setCourtEntity(cEntity);
		}
		
	}
	@Transactional
	@Override
	public Optional<CourtEntity> findCourtById(int courtId) {
		Optional<CourtEntity> courtEntity = courtRepo.findById(courtId);
		return courtEntity;
	}
	@Override
	public List<CourtBranchEntity> findCourtBranchByName(Set<String> courtBranchEntityList, Integer courtId) {
		List<CourtBranchEntity> branchEntities= branchRepo.getAllBranchesByName(courtBranchEntityList,courtId);
		return branchEntities;
	}
	@Override
	public void saveCourtBranchDetails(Set<CourtBranchEntity> courtBranchEntityList) {
		
		branchRepo.saveAll(courtBranchEntityList);
	}
	@Override
	public Optional<CourtBranchEntity> findCourtBranchById(int branchId) {
		Optional<CourtBranchEntity> branchEntity = branchRepo.findById(branchId);
		return branchEntity;
	}
	@Override
	public Page<CourtEntity> getCourtEntityPage(String searchValue, Pageable pageableRequest) {
		Page<CourtEntity> courtEntityPage = courtRepo.findActiveCourtById(searchValue,pageableRequest);
		return courtEntityPage;
	}
	@Override
	public Page<CourtBranchEntity> getAllCourtEntityPage(Pageable pageableRequest) {
		Page<CourtBranchEntity> courtEntityPage = branchRepo.findAllActiveCourt(pageableRequest);
		return courtEntityPage;
		
		
	}

}
