package com.SuperemeAppealReporter.api.ui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SuperemeAppealReporter.api.constant.AppConstant;
import com.SuperemeAppealReporter.api.constant.RestMappingConstant;
import com.SuperemeAppealReporter.api.service.FirebaseNotificationService;
import com.SuperemeAppealReporter.api.ui.model.request.RegisterDeviceRequest;
import com.SuperemeAppealReporter.api.ui.model.request.SendNotificationRequest;
import com.SuperemeAppealReporter.api.ui.model.response.BaseApiResponse;
import com.SuperemeAppealReporter.api.ui.model.response.CommonMessageResponse;
import com.SuperemeAppealReporter.api.ui.model.response.ResponseBuilder;

@RestController
@RequestMapping(path = RestMappingConstant.Notification.NOTIFICATION_BASE_URI)
public class FireBaseNotificationController {

	
	@Autowired
	private FirebaseNotificationService firebaseNotificationService;
	
	
	@PostMapping(path = RestMappingConstant.Notification.REGISTER_DEVICE_URI)
	public ResponseEntity<BaseApiResponse> registerDeviceHandler(@RequestBody RegisterDeviceRequest registerDeviceRequest) {

		CommonMessageResponse commonMessageResponse = null;
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		commonMessageResponse = firebaseNotificationService.registerDevice(registerDeviceRequest, email);
		
		if(commonMessageResponse==null){
			commonMessageResponse = new CommonMessageResponse();
			commonMessageResponse.setMsg("Internal Server Error");
			BaseApiResponse baseApiResponse = ResponseBuilder.getFailureResponse(commonMessageResponse);
			return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
		}
		
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(commonMessageResponse);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	@PostMapping(path = RestMappingConstant.Notification.SEND_NOTIFICATION_URI)
	public ResponseEntity<BaseApiResponse> sendHandler(@RequestBody SendNotificationRequest sendNotificationRequest) {
CommonMessageResponse commonMessageResponse = null;
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		commonMessageResponse = firebaseNotificationService.send(sendNotificationRequest);
		
		if(commonMessageResponse==null){
			commonMessageResponse = new CommonMessageResponse();
			commonMessageResponse.setMsg("Internal Server Error");
			BaseApiResponse baseApiResponse = ResponseBuilder.getFailureResponse(commonMessageResponse);
			return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
		}
		else 
		{
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(commonMessageResponse);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
		}
			
		
	}
	

	@PostMapping(path = RestMappingConstant.Notification.GET_NOTIFICATION_LIST)
	public ResponseEntity<BaseApiResponse> getNotificationList()
   {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Map retMap = firebaseNotificationService.getNotificationList(email);
		
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(retMap);
		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);
	}
	
	
	
	
	
}
