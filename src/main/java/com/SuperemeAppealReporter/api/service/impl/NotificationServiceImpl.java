package com.SuperemeAppealReporter.api.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.SuperemeAppealReporter.api.constant.AppConstant;
import com.SuperemeAppealReporter.api.enums.UserType;
import com.SuperemeAppealReporter.api.pojo.Mail;
import com.SuperemeAppealReporter.api.pojo.StaffMail;
import com.SuperemeAppealReporter.api.service.NotificationService;


@Service
public class NotificationServiceImpl implements NotificationService {

	
	private JavaMailSender javaMailSender;
	
    @Autowired
    SpringTemplateEngine templateEngine;
	
	@Autowired
	public NotificationServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	@Async
	@Override
	public void sendEmailNotification(Mail mail, String updateFlag) throws IOException, MessagingException {
		  MimeMessage message = javaMailSender.createMimeMessage();
	       
	        MimeMessageHelper helper = new MimeMessageHelper(message,
	                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                StandardCharsets.UTF_8.name());

	       // helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

	        Context context = new Context();
	        
	        context.setVariables(mail.getModel());
	        
	        String html = "";
	        if(updateFlag.equals("N")){
	        if(mail.getBelongsTo().equals(UserType.USER) && mail.getSubject().equals(AppConstant.Mail.OnBoardingMail.SUBJECT))
	             html = templateEngine.process("email-template-user-onboarding", context);
	      
	        if(mail.getBelongsTo().equals(UserType.USER) && mail.getSubject().equals(AppConstant.Mail.OnBoardingMail.CUSTOM_SUBJECT))
	             html = templateEngine.process("email-template-user-custom-onboarding", context);
  	
	        
	        else  if(mail.getBelongsTo().equals(UserType.USER) && mail.getSubject().equals(AppConstant.Mail.ForgetPasswordMail.SUBJECT))
	        {
	        	System.out.println("---------------");
	        	html = templateEngine.process("email-template-user-forgetPassword", context);
	        }
	        }
	        else{
	        	if(mail.getBelongsTo().equals(UserType.USER))
		             html = templateEngine.process("email-template-updated-user-onboarding", context);
	        }
	        helper.setFrom("system@saronline.co.in");
	     
	        helper.setTo(mail.getTo());
	        helper.setText(html, true);
	        helper.setSubject(mail.getSubject());
	  
	        //javaMailSender.setDefaultEncoding("UTF-8");
	        
	        javaMailSender.send(message);
		
	}

	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		javaMailSender.send(simpleMailMessage);
		
	}


	@Override
	public void sendStaffEmailNotification(StaffMail onBoardingMail, String updateFlag ) throws MessagingException {

		  MimeMessage message = javaMailSender.createMimeMessage();
	       
	        MimeMessageHelper helper = new MimeMessageHelper(message,
	                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                StandardCharsets.UTF_8.name());

	       // helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

	        Context context = new Context();
	        
	        context.setVariables(onBoardingMail.getModel());
	        
	        String html = "";
	        if(updateFlag.equalsIgnoreCase("N")){
	        if(!(onBoardingMail.getBelongsTo().equals("USER")) && onBoardingMail.getSubject().equals(AppConstant.Mail.OnBoardingMail.SUBJECT))
	             html = templateEngine.process("email-template-staff-onboarding.html", context);
	      
	        if(!(onBoardingMail.getBelongsTo().equals("USER")) && onBoardingMail.getSubject().equals(AppConstant.Mail.OnBoardingMail.CUSTOM_SUBJECT))
	             html = templateEngine.process("email-template-staff-custom-onboarding", context);
	        
	        else  if(!(onBoardingMail.getBelongsTo().equals("USER")) && onBoardingMail.getSubject().equals(AppConstant.Mail.ForgetPasswordMail.SUBJECT))
	             html = templateEngine.process("email-template-staff-forgetPassword", context);
	        } 
	        else if(updateFlag.equalsIgnoreCase("Y")){
	        	if(!(onBoardingMail.getBelongsTo().equals("USER")))
		             html = templateEngine.process("email-template-updated-staff-onboarding.html", context);
	        }
	        helper.setFrom("system@saronline.co.in");
	       
	        helper.setTo(onBoardingMail.getTo());
	        helper.setText(html, true);
	        helper.setSubject(onBoardingMail.getSubject());
	  
	        //javaMailSender.setDefaultEncoding("UTF-8");
	        
	        javaMailSender.send(message);
		
	
		
	}

	
	
	
}
