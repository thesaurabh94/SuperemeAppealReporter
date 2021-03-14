package com.SuperemeAppealReporter.api.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuperemeAppealReporter.api.bo.AddPlanBo;
import com.SuperemeAppealReporter.api.bo.DeletePlanBo;
import com.SuperemeAppealReporter.api.bo.GetPlanListBo;
import com.SuperemeAppealReporter.api.constant.AppConstant;
import com.SuperemeAppealReporter.api.constant.RestMappingConstant;
import com.SuperemeAppealReporter.api.converter.AdminConverter;
import com.SuperemeAppealReporter.api.service.SubscriptionPlanService;
import com.SuperemeAppealReporter.api.ui.model.request.AddPlanRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeletePlanRequest;
import com.SuperemeAppealReporter.api.ui.model.request.EditPlan;
import com.SuperemeAppealReporter.api.ui.model.request.GetPlanListRequest;
import com.SuperemeAppealReporter.api.ui.model.response.BaseApiResponse;
import com.SuperemeAppealReporter.api.ui.model.response.CommonMessageResponse;
import com.SuperemeAppealReporter.api.ui.model.response.CommonPaginationResponse;
import com.SuperemeAppealReporter.api.ui.model.response.ResponseBuilder;

@RestController
@RequestMapping(path =RestMappingConstant.Admin.ADMIN_BASE_URI)
public class SubscriptionPlanController {

	@Autowired SubscriptionPlanService subscriptionPlanService;
	
	/*****************************Add Plan API***************************/
	@PostMapping(path = RestMappingConstant.SubscriptionPlan.ADD_PLAN_URI)
	public ResponseEntity<BaseApiResponse> addPlan(@Valid @RequestBody AddPlanRequest addPlanRequest){
		
		/*****************************Converting Plan Request to Bo***************************/
		AddPlanBo addPlanBo = AdminConverter.convertAddPlanRequestToAddPlanBo(addPlanRequest);
		
		/*****************************Sending request to Service Layer***************************/
		CommonMessageResponse response = subscriptionPlanService.addPlan(addPlanBo);
		
		
		/** Generating Response **/
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(response);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@PostMapping(path = RestMappingConstant.SubscriptionPlan.DELETE_PLAN_URI)
	public ResponseEntity<BaseApiResponse> deletePlan(@Valid @RequestBody DeletePlanRequest deletePlanRequest){
		
		/*****************************Converting Plan Request to Bo***************************/
		DeletePlanBo deletePlanBo = AdminConverter.convertDeletePlanRequestToDeletePlanBo(deletePlanRequest);
		
		/*****************************Sending request to Service Layer***************************/
		CommonMessageResponse response = subscriptionPlanService.deletePlan(deletePlanBo);
		
		
		/** Generating Response **/
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(response);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	
	
	@PostMapping(path = RestMappingConstant.SubscriptionPlan.GET_PLAN_URI)
	public ResponseEntity<BaseApiResponse> getPlanList(@Valid @RequestBody GetPlanListRequest getPlanListRequest,
			@RequestParam(name = AppConstant.CommonConstant.PAGE_NUMBER, defaultValue = "1") int pageNumber,
			@RequestParam(name = AppConstant.CommonConstant.PAGE_LIMIT, defaultValue = "8") int perPageLimit){
			
		/*****************************Converting Plan Request to Bo***************************/
		GetPlanListBo getPlanListBo = AdminConverter.convertDeleteGetPlanListRequestToGetPlanListBo(getPlanListRequest);
		
		/** Calling service **/
		CommonPaginationResponse commonPaginationResponse = subscriptionPlanService.getPlanList(pageNumber,
				perPageLimit, getPlanListBo);

		/** Generating Response **/
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(commonPaginationResponse);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
		
	}
	
	/*****************************Add Plan API***************************/
	@PostMapping(path = RestMappingConstant.SubscriptionPlan.EDIT_PLAN_URI)
	public ResponseEntity<BaseApiResponse> editPlan(@Valid @RequestBody EditPlan editPlan){
		
		/*****************************Converting Plan Request to Bo***************************/
	/*	AddPlanBo addPlanBo = AdminConverter.convertAddPlanRequestToAddPlanBo(addPlanRequest);*/
		
		/*****************************Sending request to Service Layer***************************/
		CommonMessageResponse response = subscriptionPlanService.editPlan(editPlan);
		
		
		/** Generating Response **/
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(response);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
}
