package com.SuperemeAppealReporter.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SuperemeAppealReporter.api.bo.AddCourtBo;
import com.SuperemeAppealReporter.api.bo.AddCourtBranchBo;
import com.SuperemeAppealReporter.api.bo.DeleteCourtBo;
import com.SuperemeAppealReporter.api.bo.DeleteCourtBranchBo;
import com.SuperemeAppealReporter.api.bo.GetCourtBo;
import com.SuperemeAppealReporter.api.constant.ErrorConstant;
import com.SuperemeAppealReporter.api.constant.SucessMessage;
import com.SuperemeAppealReporter.api.exception.type.AppException;
import com.SuperemeAppealReporter.api.io.dao.CourtDao;
import com.SuperemeAppealReporter.api.io.entity.CourtBranchEntity;
import com.SuperemeAppealReporter.api.io.entity.CourtEntity;
import com.SuperemeAppealReporter.api.io.repository.CourtBranchRepository;
import com.SuperemeAppealReporter.api.io.repository.CourtRepository;
import com.SuperemeAppealReporter.api.service.CourtService;
import com.SuperemeAppealReporter.api.ui.model.response.CommonMessageResponse;
import com.SuperemeAppealReporter.api.ui.model.response.CommonPaginationResponse;
import com.SuperemeAppealReporter.api.ui.model.response.GetCourtDropDownResponse;
import com.SuperemeAppealReporter.api.ui.model.response.GetCourtResponse;

@Service
public class CourtServiceImpl implements CourtService{

	@Autowired CourtDao courtDao;
	@Autowired CourtRepository courtRepository;
	@Autowired CourtBranchRepository courtBranchRepository;
	
	@Transactional
	@Override
	public CommonMessageResponse addCourt(AddCourtBo addCourtBo) {
		String courtName = addCourtBo.getCourtName();
		
		List<String> courtBranchList = new ArrayList<String>();
		if(!addCourtBo.getCourtBranchName().isEmpty() || addCourtBo.getCourtBranchName()!=null)
			courtBranchList.addAll(addCourtBo.getCourtBranchName());
		
		//CourtEntity courtEntity = courtDao.findCourtByName(courtName);
		CourtEntity courtEntity = new CourtEntity();
		courtEntity.setCourtType(courtName);
		Set<CourtBranchEntity> courtBranchEntityList = new HashSet<CourtBranchEntity>();
		for(String branchList: courtBranchList){
			CourtBranchEntity branchEntity = new CourtBranchEntity();
			branchEntity.setBranchName(branchList);
			courtBranchEntityList.add(branchEntity);
		}
		courtEntity.setCourtBranchSet(courtBranchEntityList);
		courtDao.saveCourtDetails(courtEntity);
		CommonMessageResponse response  = new CommonMessageResponse();
		response.setMsg(SucessMessage.Court.ADDED_SUCCESS);
		return response;
	}

	@Transactional
	@Override
	public CommonMessageResponse deleteCourt(DeleteCourtBo deleteCourtBo) {
		
		int courtId = Integer.parseInt(deleteCourtBo.getCourtId());
		
		Optional<CourtEntity> courtEntity= courtDao.findCourtById(courtId);
		courtEntity.get().setActive(false);
		Set<CourtBranchEntity> courtBranchEntityList = new HashSet<CourtBranchEntity>();
		courtBranchEntityList.addAll(courtEntity.get().getCourtBranchSet());
		for(CourtBranchEntity branchEntity: courtBranchEntityList){
			branchEntity.setActive(false);
		}
		CommonMessageResponse response  = new CommonMessageResponse();
		response.setMsg(SucessMessage.Court.DELETE_SUCCESS);
		return response;
	}

	@Override
	public CommonMessageResponse addCourtBranch(AddCourtBranchBo addCourtBranchBo) {
		int courtId = Integer.parseInt(addCourtBranchBo.getCourtId());
		
		HashMap<String, String> existingBrnaches = new HashMap<String, String>();
		
		/*************************Validating if court id already exists or not*************************/
		CourtEntity courtEntity = courtDao.findCourtById(courtId).orElseThrow(() -> new AppException(ErrorConstant.InvalidCourtIdError.ERROR_TYPE,
				ErrorConstant.InvalidCourtIdError.ERROR_CODE,
				ErrorConstant.InvalidCourtIdError.ERROR_MESSAGE));
		
		/*************************Fetching all already mapped branches for this court*************************/
		List<CourtBranchEntity> branchEntityList= courtDao.findCourtBranchByName(addCourtBranchBo.getCourtBranchName(),courtEntity.getId());
		
		/*************************Adding existing mapping into map*************************/
		for(CourtBranchEntity existingBranchList : branchEntityList){
			existingBrnaches.put(existingBranchList.getBranchName(), existingBranchList.getBranchName());
		}
		
		List<String> courtBranchList = new ArrayList<String>();
		
		/*************************Adding all branch request into a List*************************/
		if(!addCourtBranchBo.getCourtBranchName().isEmpty() || addCourtBranchBo.getCourtBranchName()!=null)
			courtBranchList.addAll(addCourtBranchBo.getCourtBranchName());
		
		Set<CourtBranchEntity> courtBranchEntityList = new HashSet<CourtBranchEntity>();
		
		/*************************Adding Non existing branches into final list*************************/
		for(String branchList: courtBranchList){
			CourtBranchEntity branchEntity = new CourtBranchEntity();
			if(!existingBrnaches.containsKey(branchList)){
			branchEntity.setBranchName(branchList);
			branchEntity.setCourtEntity(courtEntity);
			courtBranchEntityList.add(branchEntity);
			}
		}
		
		/*************************Saving the record*************************/
		courtDao.saveCourtBranchDetails(courtBranchEntityList);
		CommonMessageResponse response  = new CommonMessageResponse();
		response.setMsg(SucessMessage.Court.BRANCH_ADDED_SUCCESS);
		return response;
	}

	
	@Transactional
	@Override
	public CommonMessageResponse deleteCourtBranch(DeleteCourtBranchBo deleteCourtBranchBo) {
		
		int branchId = Integer.parseInt(deleteCourtBranchBo.getCourtBranchId());
		
		CourtBranchEntity courtBranchEntity= courtDao.findCourtBranchById(branchId).orElseThrow(() -> new AppException(ErrorConstant.InvalidCourtBranchIdError.ERROR_TYPE,
				ErrorConstant.InvalidCourtBranchIdError.ERROR_CODE,
				ErrorConstant.InvalidCourtBranchIdError.ERROR_MESSAGE));
		
		courtBranchEntity.setActive(false);
		
		CommonMessageResponse response  = new CommonMessageResponse();
		response.setMsg(SucessMessage.Court.DELETE_BRANCH_SUCCESS);
		return response;
	}

	@Override
	public CommonPaginationResponse getCourtService(GetCourtBo getCourtBo, int pageNumber, int perPageLimit) {

		
		CommonPaginationResponse commonPaginationResponse =  null;
		
		try
		{
		
		
	
		if (pageNumber > 0)
			pageNumber = pageNumber - 1;
		
		
		
		Pageable pageableRequest = PageRequest.of(pageNumber, perPageLimit);
		Page<CourtBranchEntity> courtEntityPage = null;
			if (getCourtBo.getSearchValue() != "" && getCourtBo.getSearchValue() != null) {
			
				courtEntityPage = courtBranchRepository.findAllActiveCourtBySearch(pageableRequest, getCourtBo.getSearchValue());
				
			}

			else
				courtEntityPage = courtDao.getAllCourtEntityPage(pageableRequest);
			
		List<CourtBranchEntity> courtEntityList = courtEntityPage.getContent();
		
		List<GetCourtResponse> getCourtResponseList = new ArrayList<GetCourtResponse>();
		for(CourtBranchEntity courtBranchEntity : courtEntityList)
		{
			GetCourtResponse getCourtResponse = new GetCourtResponse();
			getCourtResponse.setCourtBranch(courtBranchEntity.getBranchName());
			getCourtResponse.setCourtBranchId(courtBranchEntity.getId());
			getCourtResponse.setCourtId(courtBranchEntity.getCourtEntity().getId());
			getCourtResponse.setCourtName(courtBranchEntity.getCourtEntity().getCourtType());
			getCourtResponseList.add(getCourtResponse);
			
		}
		
		
		
		commonPaginationResponse = new CommonPaginationResponse();
		commonPaginationResponse.setTotalNumberOfPagesAsPerGivenPageLimit(courtEntityPage.getTotalPages());
		commonPaginationResponse.setOjectList(getCourtResponseList);
		}
		catch(AppException appException)
		{
			throw appException;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String errorMessage = "Error in CourtServiceImpl --> getCourtService()";
			AppException appException = new AppException("Type : " + ex.getClass()
			+ ", " + "Cause : " + ex.getCause() + ", " + "Message : " + ex.getMessage(),ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;
			
		}
		
		return commonPaginationResponse;
	
	}

	@Override
	public CommonPaginationResponse getCourtServiceV2(GetCourtBo getCourtBo, int pageNumber, int perPage) {
	
		if(pageNumber<=0)
			pageNumber=1;
		CommonPaginationResponse commonPaginationResponse = null;
		int recordCountFinal = 0;
		List<GetCourtResponse> getCourtResponseList = new ArrayList<GetCourtResponse>();
		if(getCourtBo.getSearchValue()=="")
		{
		     Iterable<CourtEntity> courtEntityList = courtRepository.findAll();
	/*	     int recordCount = 0;
		     
				recordCount++;
				recordCountFinal = recordCount;
				if (recordCount == perPage) {
					pageCount++;
				}
				if (pageCount == pageNumber) {*/
		 	 int recordCount = 0;
		 	 int pageCount = 0;
		     for (CourtEntity courtEntity : courtEntityList) {
		    	
					
		    	 if(pageNumber==pageCount)
		    	 {
					for (CourtBranchEntity courtBranch : courtEntity.getCourtBranchSet()) {
						
						GetCourtResponse getCourtResponse = new GetCourtResponse();
						getCourtResponse.setCourtName(courtEntity.getCourtType());
						getCourtResponse.setCourtBranch(courtBranch.getBranchName());
						getCourtResponse.setCourtBranchId(courtBranch.getId());
						getCourtResponse.setCourtId(courtEntity.getId());
						getCourtResponseList.add(getCourtResponse);
						recordCount++;
						if(recordCount==perPage)
						{
							break ;
						}
					}
		    	 }
					if(recordCount==perPage)
					{
						pageCount++;
						break;
					}
						
	
			}
		     
		     

		}
	
		
	commonPaginationResponse = new CommonPaginationResponse();
	commonPaginationResponse.setTotalNumberOfPagesAsPerGivenPageLimit(recordCountFinal);
	commonPaginationResponse.setOjectList(getCourtResponseList);
		return commonPaginationResponse;
	}

	@Override
	public List<GetCourtDropDownResponse> getOnlyCourtList() {
		
		List<GetCourtDropDownResponse> getCourtDropdownResponse = null;
		
		try
		{
		Iterable<CourtEntity> courtEntityList = courtRepository.findAll();
		
		getCourtDropdownResponse = new ArrayList<GetCourtDropDownResponse>();
	
		if(courtEntityList==null)
		{
			return getCourtDropdownResponse;
		}
		
		for(CourtEntity court : courtEntityList)
		{
			GetCourtDropDownResponse resp = new GetCourtDropDownResponse();
			if(court.getActive())
			{
				resp.setId(court.getId());
				resp.setValue(court.getCourtType());
				resp.setLabel(court.getCourtType());
				getCourtDropdownResponse.add(resp);
			}
		}
	}
		catch(AppException appException)
		{
			throw appException;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String errorMessage = "Error in CourtServiceImpl --> getOnlyCourtList()";
			AppException appException = new AppException("Type : " + ex.getClass()
			+ ", " + "Cause : " + ex.getCause() + ", " + "Message : " + ex.getMessage(),ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;
			
		}
		return getCourtDropdownResponse;
	}

	@Override
	public List<GetCourtDropDownResponse> getCourtBranchByCourtId(int courtId) {
		
		List<GetCourtDropDownResponse> getCourtDropDownResponseList = null;
		
		try
		{
		getCourtDropDownResponseList = new ArrayList<GetCourtDropDownResponse>();
		
		CourtEntity courtEntity = courtDao.findCourtById(courtId).orElseThrow(()-> 
		new AppException(ErrorConstant.GetCourtBranchError.ERROR_TYPE,
				ErrorConstant.GetCourtBranchError.ERROR_CODE,
				ErrorConstant.GetCourtBranchError.ERROR_MESSAGE));
		
		Set<CourtBranchEntity> presentBranchSet = courtEntity.getCourtBranchSet();
		
		for(CourtBranchEntity courtBranchEntity : presentBranchSet)
		{
			GetCourtDropDownResponse getCourtDropDownResponse = new GetCourtDropDownResponse();
			getCourtDropDownResponse.setId(courtBranchEntity.getId());
			getCourtDropDownResponse.setLabel(courtBranchEntity.getBranchName());
			getCourtDropDownResponse.setValue(courtBranchEntity.getBranchName());
			getCourtDropDownResponseList.add(getCourtDropDownResponse);
		}
		}
		
		catch(AppException appException)
		{
			throw appException;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			String errorMessage = "Error in CourtServiceImpl --> getCourtBranchByCourtId()";
			AppException appException = new AppException("Type : " + ex.getClass()
			+ ", " + "Cause : " + ex.getCause() + ", " + "Message : " + ex.getMessage(),ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;
			
		}
		return getCourtDropDownResponseList;
		
	}
}
