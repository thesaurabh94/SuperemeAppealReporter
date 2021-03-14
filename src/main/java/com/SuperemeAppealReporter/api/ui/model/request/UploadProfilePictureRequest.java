package com.SuperemeAppealReporter.api.ui.model.request;

import org.springframework.web.multipart.MultipartFile;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadProfilePictureRequest {

	private MultipartFile file;
	private String userEmail;
}
