/*package com.SuperemeAppealReporter.api.ui.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SuperemeAppealReporter.api.firebase.AndroidPushNotificationService;

@RestController
@RequestMapping(path = "/SuperemeAppealReporter/v1/api/testNotification")
public class FireBaseTestController {
	
	@Autowired
	private AndroidPushNotificationService androidPushNotificationService;
	
	private final String TOPIC = "JavaSampleApproach";
	
	 @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
	  public ResponseEntity<String> send() throws JSONException {
	 
	    JSONObject body = new JSONObject();
	    body.put("to", "/topics/" + TOPIC);
	    body.put("priority", "high");
	 
	    JSONObject notification = new JSONObject();
	    notification.put("title", "JSA Notification");
	    notification.put("body", "Happy Message!");
	    
	    JSONObject data = new JSONObject();
	    data.put("Key-1", "JSA Data 1");
	    data.put("Key-2", "JSA Data 2");
	 
	    body.put("notification", notification);
	    body.put("data", data);
	 
	*//**
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
	*//*
	 
	    HttpEntity<String> request = new HttpEntity<>(body.toString());
	 
	    CompletableFuture<String> pushNotification = androidPushNotificationService.send(request);
	    CompletableFuture.allOf(pushNotification).join();
	 
	    try {
	      String firebaseResponse = pushNotification.get();
	      
	      return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    }
	 
	    return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	  }
	

}
*/