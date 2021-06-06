package com.SuperemeAppealReporter.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SuperemeAppealReporter.api.exception.type.AppException;
import com.SuperemeAppealReporter.api.firebase.AndroidPushNotificationService;
import com.SuperemeAppealReporter.api.io.entity.FirebaseGenericNotificationEntity;
import com.SuperemeAppealReporter.api.io.entity.FirebaseUserDeviceMappingEntity;
import com.SuperemeAppealReporter.api.io.entity.FirebaseUserNotificationEntity;
import com.SuperemeAppealReporter.api.io.entity.UserEntity;
import com.SuperemeAppealReporter.api.io.repository.FirebaseGenericNotificationRepository;
import com.SuperemeAppealReporter.api.io.repository.FirebaseUserDeviceMappingRepository;
import com.SuperemeAppealReporter.api.io.repository.FirebaseUserNotificationRepository;
import com.SuperemeAppealReporter.api.service.FirebaseNotificationService;
import com.SuperemeAppealReporter.api.service.UserService;
import com.SuperemeAppealReporter.api.ui.model.request.RegisterDeviceRequest;
import com.SuperemeAppealReporter.api.ui.model.request.SendNotificationRequest;
import com.SuperemeAppealReporter.api.ui.model.response.CommonMessageResponse;
import com.SuperemeAppealReporter.api.ui.model.response.GetNotificationListResponse;

@Service
public class FirebaseNotificationServiceImpl implements FirebaseNotificationService{

	@Autowired
	private UserService userService;
	
	
	
	@Autowired 
	private FirebaseUserDeviceMappingRepository firebaseUserDeviceMappingRepository;
	
	@Autowired
	private FirebaseGenericNotificationRepository firebaseGenericNotificationRepository;
	
	@Autowired
	private FirebaseUserNotificationRepository firebaseUserNotificationRepository;
	
	@Autowired
	private AndroidPushNotificationService androidPushNotificationService;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public CommonMessageResponse registerDevice(RegisterDeviceRequest registerDeviceRequest, String email) {
		
		// TODO Auto-generated method stub
		CommonMessageResponse commonMessageResponse = null;
		try
		{
		UserEntity userEntity = userService.findByEmail(email);
		
		
		/**Preparing Firebase User Specific Entity **/
		FirebaseUserDeviceMappingEntity firebaseEntity  = null;

        firebaseEntity = firebaseUserDeviceMappingRepository.getEntityByDeviceId(registerDeviceRequest.getDeviceId());
		
        if(firebaseEntity!=null){
        	 firebaseEntity.setUserEntity(userEntity);
        	firebaseEntity.setUserLoggedIn(true);
        }
        else {
		 firebaseEntity = new FirebaseUserDeviceMappingEntity();
		 firebaseEntity.setDeviceId(registerDeviceRequest.getDeviceId());
		 firebaseEntity.setUserEntity(userEntity);
		 firebaseEntity.setUserLoggedIn(true);
		 firebaseUserDeviceMappingRepository.save(firebaseEntity);
        }
        commonMessageResponse = new CommonMessageResponse();
        commonMessageResponse.setMsg("Device Id Saved Successfully");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return commonMessageResponse;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public CommonMessageResponse send(SendNotificationRequest sendRequest) {
		
		CommonMessageResponse commonMessageResponse = null;
		FirebaseGenericNotificationEntity firebaseGenericNotificationEntity = null;
		FirebaseUserNotificationEntity firebaseUserNotificationEntity  = null;
		boolean sendNotification = true;
		try
		{
			commonMessageResponse = new CommonMessageResponse();
		String[] deviceIdArray = null;
		if(sendRequest.getCategory().equals("CASE")){
			
		   deviceIdArray = firebaseUserDeviceMappingRepository.getAllDeviceId();
			
		   firebaseGenericNotificationEntity = new FirebaseGenericNotificationEntity();
		   firebaseGenericNotificationEntity.setNotificationType("CASE");
		   firebaseGenericNotificationEntity.setTitle(sendRequest.getTitle());
		   firebaseGenericNotificationEntity.setBody(sendRequest.getBody());
		   firebaseGenericNotificationEntity.setDocId(sendRequest.getDocId());
		   firebaseGenericNotificationEntity.setStatus("200");
		   firebaseGenericNotificationEntity.setRemarks("SUCCESS");
		   firebaseGenericNotificationRepository.save(firebaseGenericNotificationEntity);
		}
		else if(sendRequest.getCategory().equals("OTHER")){
			
			List<FirebaseUserDeviceMappingEntity> firebaseUserDeviceMappingEntityList = firebaseUserDeviceMappingRepository.getEntityByUserEmail(sendRequest.getUserEmail());
		    
			if(!"".equals(sendRequest.getUserEmail())) {
				
				if(firebaseUserDeviceMappingEntityList!=null){
					
					for(FirebaseUserDeviceMappingEntity firebaseUserDeviceMappingEntity : firebaseUserDeviceMappingEntityList) {
						if(firebaseUserDeviceMappingEntity.isUserLoggedIn()){
							
							deviceIdArray = new String[]{firebaseUserDeviceMappingEntity.getDeviceId()};
		                    firebaseUserNotificationEntity = new FirebaseUserNotificationEntity();
						
							firebaseUserNotificationEntity.setBody(sendRequest.getBody());
							firebaseUserNotificationEntity.setTitle(sendRequest.getTitle());
							firebaseUserNotificationEntity.setNotificationType("USER_SPECIFIC");
							firebaseUserNotificationEntity.setFirebaseUserDeviceMappingEntity(firebaseUserDeviceMappingEntity);
						   
							firebaseUserNotificationEntity.setRead(false);
							firebaseUserNotificationEntity.setStatus("200");
							firebaseUserNotificationEntity.setRemarks("SUCESS");
							firebaseUserNotificationRepository.save(firebaseUserNotificationEntity);
						}
						else {
							System.out.println("User found with a device id, but user is logged out");
							sendNotification = false;
						}
					}
					
					
				}
				else{
					System.out.println("No Device Id Found for respective user");
					sendNotification = false;
				}
				
			}
			else {
				
				 deviceIdArray = firebaseUserDeviceMappingRepository.getAllDeviceId();
				 
				 List<FirebaseUserDeviceMappingEntity> deviceMappingList = firebaseUserDeviceMappingRepository.getAllDeviceMappingList();
				 
				 for (FirebaseUserDeviceMappingEntity deviceMappingEntity : deviceMappingList) {
					 FirebaseUserNotificationEntity newFirebaseUserNotificationEntity = new FirebaseUserNotificationEntity();	 
					 newFirebaseUserNotificationEntity.setBody(sendRequest.getBody());
					 newFirebaseUserNotificationEntity.setTitle(sendRequest.getTitle());
					 newFirebaseUserNotificationEntity.setNotificationType("USER_SPECIFIC");
					 newFirebaseUserNotificationEntity.setFirebaseUserDeviceMappingEntity(deviceMappingEntity);
					 newFirebaseUserNotificationEntity.setRead(false);
					 newFirebaseUserNotificationEntity.setStatus("200");
					 newFirebaseUserNotificationEntity.setRemarks("SUCESS");
						firebaseUserNotificationRepository.save(newFirebaseUserNotificationEntity);
					 
				 }
				 
				 
			}
			
			
		}
		
		if(sendNotification){
			
		//	String[] deviceIdArray1 = {"cEDyH2z6Raqgy1CNqeIUE8:APA91bHpfG8owAAqHK2yy8Z9sakX2wbq4MfcT0GTE4aZwrWuLVLDlTUxkCclbnfih-n8vN1zlGDpeNXMjw_hS-LwB3AijmQfTmlqYwxe-9AuP5IouGhg14lzJGJXwsMDdeZut0OTJUc-"};
		/** Now send the notification **/
		 JSONObject body = new JSONObject();
		    body.put("registration_ids", deviceIdArray);
		   // body.put("to", "/topics/sarmobile");
		 
		    JSONObject notification = new JSONObject();
		    notification.put("title", sendRequest.getTitle());
		    notification.put("body", sendRequest.getBody());
		    notification.put("docId", sendRequest.getDocId());
		    notification.put("content_available", true);
		    notification.put("priority", "high");
		    notification.put("action", "42");
		    
		    JSONObject data = new JSONObject();
		    data.put("title", sendRequest.getTitle());
		    data.put("body", sendRequest.getBody());
		    data.put("docId", sendRequest.getDocId());
		    data.put("content_available", true);
		    data.put("priority", "high");
		    data.put("action", "42");
		    data.put("channelId","test-channel");
		    
		 
		    body.put("notification", notification);
		    body.put("data", data);
		    
		 
		/**
		    {
		       "notification": {
		          "title": "JSA Notification",
		          "body": "Happy Message!"
		       },
		       "data": {
		          "Key-1": "JSA Data 1",
		          "Key-2": "JSA Data 2"
		       },
		       "to": "/topics/JavaSampleApproach",
		       "priority": "high"
		    }
		*/
		    
		    /*
		     * {
      "registration_ids": [
                     "cEDyH2z6Raqgy1CNqeIUE8:APA91bHpfG8owAAqHK2yy8Z9sakX2wbq4MfcT0GTE4aZwrWuLVLDlTUxkCclbnfih-n8vN1zlGDpeNXMjw_hS-LwB3AijmQfTmlqYwxe-9AuP5IouGhg14lzJGJXwsMDdeZut0OTJUc-"
                    ],
     "notification" : {
        "body" : "The test Firebase",
        "title" : "test React Native Firebase",
        "content_available" : true,
        "priority" : "high",
        "action":"42"
    },
    "data" : {
        "body" : "The test Firebase",
        "title" : "test React Native Firebase",
        "content_available" : true,
        "priority" : "high",
        "channelId" : "test-channel",
        "action":"42"
    }
    }
		     */
		 
		    
		    HttpEntity<String> request = new HttpEntity<>(body.toString());
		 System.out.println("---Firebase JSON----"); 
		 System.out.println(body.toString());
				/* CompletableFuture<String> pushNotification = */
				 String firebaseResponse =  androidPushNotificationService.send(request);
		
		  //  String firebaseResponse = pushNotification.get();
		    System.out.println("--------firebaseResponse----------------: "+firebaseResponse);
		      commonMessageResponse.setMsg("Notification Sent Success. Response from FireBase : <"+firebaseResponse+">");
		
		      if(firebaseResponse.contains("error")){
		    	  throw new AppException("FIREBASE_FAILER","500","FIRE BASE RESPONSE NOT SUCCESS");
		      }
		}
		
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			
			if("CASE".equals(sendRequest.getCategory())){
				if(firebaseGenericNotificationEntity!=null){
					firebaseGenericNotificationEntity.setStatus("500");
					firebaseGenericNotificationEntity.setRemarks("FAILURE");
				}
			}
			else {
				if(firebaseUserNotificationEntity!=null){
					System.out.println("--NOT NULL USER----");
					firebaseUserNotificationEntity.setStatus("500");
					firebaseUserNotificationEntity.setRemarks("FAILURE");
				}
				else {
					System.out.println("-NULL USER-");
				}
				
			}
			if(ex.getLocalizedMessage().contains("FIRE BASE RESPONSE NOT SUCCESS")){
				commonMessageResponse.setMsg("FIREBASE API HIT IS SUCCESS. BUT RESPONSE IS A FAILURE");
						
			}
			
			
		}
		return commonMessageResponse;
	}

	@Override
	public Map getNotificationList(String email) {
		
		Map retMap = new HashMap();
		UserEntity userEntity = userService.findByEmail(email);
		
		List<FirebaseUserDeviceMappingEntity> firebaseUserDeviceMappingEntityList = firebaseUserDeviceMappingRepository.getEntityByUserEmail(email);
		
		List<FirebaseUserNotificationEntity> totalUserNotificationList = new ArrayList<FirebaseUserNotificationEntity>(); 
		List<FirebaseGenericNotificationEntity> totalGenericNotificationList = new ArrayList<FirebaseGenericNotificationEntity>(); 
		
		List<GetNotificationListResponse> notificationList = new ArrayList<GetNotificationListResponse>();
		
		for(FirebaseUserDeviceMappingEntity deviceMappingEntity : firebaseUserDeviceMappingEntityList) {
			
			Integer deviceId = deviceMappingEntity.getId();
			
			List<FirebaseUserNotificationEntity> userNotificationList = firebaseUserNotificationRepository.findByDeviceIdAndStatus(deviceId);
			List<FirebaseGenericNotificationEntity> genericNotificationList = firebaseGenericNotificationRepository.findNotificationsBetweenDates();			
			totalUserNotificationList.addAll(userNotificationList);
			totalGenericNotificationList.addAll(genericNotificationList);
			
			for(FirebaseUserNotificationEntity userNotification : userNotificationList) {
				
				GetNotificationListResponse resp = new GetNotificationListResponse();
				
				resp.setBody(userNotification.getBody());
				resp.setCategory(userNotification.getNotificationType());
				resp.setDocId("");
				resp.setTitle(userNotification.getTitle());
				resp.setUserEmail(email);
				resp.setNotificationTime(userNotification.getCreatedDate().getTime());
				notificationList.add(resp);
			}
			
			
			for(FirebaseGenericNotificationEntity genericNotification : genericNotificationList) {
				
				GetNotificationListResponse resp = new GetNotificationListResponse();
				
				resp.setBody(genericNotification.getBody());
				resp.setCategory(genericNotification.getNotificationType());
				resp.setDocId(genericNotification.getDocId());
				resp.setTitle(genericNotification.getTitle());
				resp.setUserEmail(email);
				resp.setNotificationTime(genericNotification.getCreatedDate().getTime());
				notificationList.add(resp);
			}
			
		}
		
		List<GetNotificationListResponse> filteredNotificationList= notificationList.stream().filter(e -> e.getUserEmail().equals(email)).collect(Collectors.toList());
		
		Collections.sort(notificationList, new Comparator<GetNotificationListResponse>() {

			@Override
			public int compare(GetNotificationListResponse o1, GetNotificationListResponse o2) {
				
				int i = o1.getNotificationTime().compareTo(o2.getNotificationTime());
				
				if(i == 0)
					return i;
				
				return -i;
				
			}
			
		});
		
		retMap.put("notificationList", notificationList);
		
		
		return retMap;
	}
	
	

	
}
