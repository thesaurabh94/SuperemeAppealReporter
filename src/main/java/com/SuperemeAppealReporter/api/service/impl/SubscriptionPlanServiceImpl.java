package com.SuperemeAppealReporter.api.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SuperemeAppealReporter.api.bo.AddPlanBo;
import com.SuperemeAppealReporter.api.bo.DeletePlanBo;
import com.SuperemeAppealReporter.api.bo.GetPlanListBo;
import com.SuperemeAppealReporter.api.constant.ErrorConstant;
import com.SuperemeAppealReporter.api.constant.SucessMessage;
import com.SuperemeAppealReporter.api.constant.SucessMessage.SubscriptionMessage;
import com.SuperemeAppealReporter.api.converter.AdminConverter;
import com.SuperemeAppealReporter.api.enums.SubscriptionType;
import com.SuperemeAppealReporter.api.exception.type.AppException;
import com.SuperemeAppealReporter.api.io.dao.SubscriptionPlanDao;
import com.SuperemeAppealReporter.api.io.entity.SubscriptionPlanEntity;
import com.SuperemeAppealReporter.api.io.entity.UserEntity;
import com.SuperemeAppealReporter.api.io.entity.UserSubscriptionDetailEntity;
import com.SuperemeAppealReporter.api.io.repository.UserRepository;
import com.SuperemeAppealReporter.api.io.repository.UserSubscriptionDetailRepository;
import com.SuperemeAppealReporter.api.service.SubscriptionPlanService;
import com.SuperemeAppealReporter.api.shared.dto.PlanListDto;
import com.SuperemeAppealReporter.api.ui.model.request.EditPlan;
import com.SuperemeAppealReporter.api.ui.model.response.CommonMessageResponse;
import com.SuperemeAppealReporter.api.ui.model.response.CommonPaginationResponse;

@org.springframework.transaction.annotation.Transactional
@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService{

	@Autowired SubscriptionPlanDao subscriptionPlanDao;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserSubscriptionDetailRepository userSubscriptionDetailRepository;
	
	@Override
	public CommonMessageResponse addPlan(AddPlanBo addPlanBo) {
		
		CommonMessageResponse response = new CommonMessageResponse();
	
		/******************Checking validation for plan duration********************/
		try{
		String plantype = addPlanBo.getSubscriptionType();
		if(!(plantype.equals(SubscriptionType.DAILY.toString()) || plantype.equals(SubscriptionType.MONTHLY.toString()) || plantype.equals(SubscriptionType.WEEKLY.toString()) || plantype.equals(SubscriptionType.YEARLY.toString())))
		{
			throw new AppException(ErrorConstant.InvalidPlanTypeError.ERROR_TYPE,
					ErrorConstant.InvalidPlanTypeError.ERROR_CODE,
					ErrorConstant.InvalidPlanTypeError.ERROR_MESSAGE);
		}
		/************************Converting Bo into Entity***************************/
		SubscriptionPlanEntity planEntity = AdminConverter.convertAddPlanBoToPlanEntity(addPlanBo);
		
		if(plantype.equals(SubscriptionType.DAILY.toString()))
			planEntity.setSubscriptionType(SubscriptionType.DAILY);
		
		else if(plantype.equals(SubscriptionType.MONTHLY.toString()))
			planEntity.setSubscriptionType(SubscriptionType.MONTHLY);
		
		else if(plantype.equals(SubscriptionType.WEEKLY.toString()))
			planEntity.setSubscriptionType(SubscriptionType.WEEKLY);
		
		else if(plantype.equals(SubscriptionType.YEARLY.toString()))
			planEntity.setSubscriptionType(SubscriptionType.YEARLY);
		
		planEntity.setSubscriptionCost(Double.parseDouble(addPlanBo.getSubscriptionCost()));
		subscriptionPlanDao.savePlan(planEntity);
		}
		catch(AppException ae){
			throw ae;
		}
		catch(Exception ex)
		{
			String errorMessage = "Error in SubscriptionPlanServiceImpl --> addPlan()";
			AppException appException = new AppException("Type : " + ex.getClass()
			+ ", " + "Cause : " + ex.getCause() + ", " + "Message : " + ex.getMessage(),ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;
		}
		response.setMsg(SubscriptionMessage.PLAN_ADDED);
		return response;
	}

	@Transactional
	@Override
	public CommonMessageResponse deletePlan(DeletePlanBo deletePlanBo) {
		int planId = Integer.parseInt(deletePlanBo.getSubscriptionPlanId());
		CommonMessageResponse response = new CommonMessageResponse();
		
		SubscriptionPlanEntity planEntity  = subscriptionPlanDao.findById(planId).orElseThrow(() -> new AppException(ErrorConstant.InvalidPlanIdError.ERROR_TYPE,
				ErrorConstant.InvalidPlanIdError.ERROR_CODE,
				ErrorConstant.InvalidPlanIdError.ERROR_MESSAGE));
		
		if(!planEntity.getActive()){
			throw new AppException(ErrorConstant.InvalidPlanIdError.ERROR_TYPE,
					ErrorConstant.InvalidPlanIdError.ERROR_CODE,
					ErrorConstant.InvalidPlanIdError.PLAN_DEACTIVATED_ERROR_MESSAGE);
		}
		
		subscriptionPlanDao.deletePlan(planId);
		response.setMsg(SubscriptionMessage.PLAN_DELETED);
		return response;
	}

	@Override
	public CommonPaginationResponse getPlanList(int pageNumber, int perPageLimit, GetPlanListBo getPlanListBo) {

		CommonPaginationResponse commonPaginationResponse = new CommonPaginationResponse();

		if (pageNumber > 0)
			pageNumber = pageNumber - 1;
		List<PlanListDto> planDtoList = new ArrayList<PlanListDto>();
		Pageable pageableRequest = PageRequest.of(pageNumber, perPageLimit);
		Page<SubscriptionPlanEntity> planEntityPage = null;
		try {
			

			if (getPlanListBo.getPlanId() != null && !getPlanListBo.getPlanId().equals("")) {
				int id = Integer.parseInt(getPlanListBo.getPlanId());
				SubscriptionPlanEntity planEntity = subscriptionPlanDao.findById(id)
						.orElseThrow(() -> new AppException(ErrorConstant.InvalidPlanIdError.ERROR_TYPE,
								ErrorConstant.InvalidPlanIdError.ERROR_CODE,
								ErrorConstant.InvalidPlanIdError.ERROR_MESSAGE));

				PlanListDto planDto = new PlanListDto();
				BeanUtils.copyProperties(planEntity, planDto);
				planDtoList.add(planDto);
				commonPaginationResponse.setTotalNumberOfPagesAsPerGivenPageLimit(1);
				
			}

			else {
				planEntityPage = subscriptionPlanDao.getAllSubscriptionEntityPage(pageableRequest);
				List<SubscriptionPlanEntity> planEntityList = planEntityPage.getContent();

				/** converting plan entity list to plan entity dto **/

				for (SubscriptionPlanEntity planEntity : planEntityList) {
					PlanListDto planDto = new PlanListDto();
					BeanUtils.copyProperties(planEntity, planDto);
					planDtoList.add(planDto);
				}
				commonPaginationResponse.setTotalNumberOfPagesAsPerGivenPageLimit(planEntityPage.getTotalPages());
			}
			
			
			commonPaginationResponse.setOjectList(planDtoList);
		}

		catch (AppException appException) {
			throw appException;
		} catch (Exception ex) {
			ex.printStackTrace();
			String errorMessage = "Error in SubscriptionPlanServiceImpl --> getPlanList()";
			AppException appException = new AppException(
					"Type : " + ex.getClass() + ", " + "Cause : " + ex.getCause() + ", " + "Message : "
							+ ex.getMessage(),
					ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;

		}

		return commonPaginationResponse;
	}

	@Override
	public CommonMessageResponse editPlan(EditPlan editPlan) {
		

		CommonMessageResponse commonMessageResponse = null;
		
		try
		{

		SubscriptionPlanEntity planEntity = subscriptionPlanDao.findById(editPlan.getId()).orElseThrow(() ->new AppException(ErrorConstant.InvalidPlanIdError.ERROR_TYPE,
				ErrorConstant.InvalidPlanIdError.ERROR_CODE,
				ErrorConstant.InvalidPlanIdError.ERROR_MESSAGE));
		
		planEntity.setSubscriptionCost(Double.parseDouble(editPlan.getSubscriptionCost()));
		planEntity.setSubscriptionParellelUserCount(editPlan.getSubscriptionParellelUserCount());
		planEntity.setSubscriptionDescription(editPlan.getSubscriptionDescription());
		planEntity.setSubscriptionName(editPlan.getSubscriptionName());
		
		commonMessageResponse = new CommonMessageResponse();
		commonMessageResponse.setMsg(SucessMessage.SubscriptionMessage.PLAN_EDITED_SUCCESS);
		
		
	}
		catch (AppException appException) {
			throw appException;
		} catch (Exception ex) {
			ex.printStackTrace();
			String errorMessage = "Error in SubscriptionPlanServiceImpl --> editPlan()";
			AppException appException = new AppException(
					"Type : " + ex.getClass() + ", " + "Cause : " + ex.getCause() + ", " + "Message : "
							+ ex.getMessage(),
					ErrorConstant.InternalServerError.ERROR_CODE,
					ErrorConstant.InternalServerError.ERROR_MESSAGE + " : " + errorMessage);
			throw appException;

		}
		return commonMessageResponse;
	}

	@Transactional
	@Override
	public void checkAndUpdatePlanStatus() {
		
		List<UserEntity> userList = userRepo.getAllUsers();
		
		Date currDate = new Date();
		
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(currDate);
		Calendar endCalendar = new GregorianCalendar();
		
		
		for(UserEntity userEntity : userList) {
			
			Date userCreateDate = userEntity.getCreatedDate();
			
			if(userEntity.isTrialPlanActive() ) {
//				long diff = currDate.getTime()-userCreateDate.getTime();
//				long difference_In_Days = (diff/ (1000 * 60 * 60 * 24))% 365;
				endCalendar.setTime(userCreateDate);
				int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
				int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
				if(diffMonth > 1) {
					userEntity.setTrialPlanActive(false);
					userEntity.setSubscriptionActive(false);
				}
			}
			
			List<UserSubscriptionDetailEntity> userSubscriptionDetailEntityList = userSubscriptionDetailRepository.findByUserId(userEntity.getId(), true);
			
			for(UserSubscriptionDetailEntity subscriptionPan : userSubscriptionDetailEntityList) {
				
				if(subscriptionPan.getEndDate().compareTo(currDate)<0) {
					subscriptionPan.setIs_plan_active(false);
					userEntity.setSubscriptionActive(false);
				}
				
			}
			
		}
		
	}

}
