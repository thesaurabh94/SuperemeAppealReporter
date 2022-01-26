package com.SuperemeAppealReporter.api.constant;

public interface SecurityConstant {

	String HEADER_STRING = "Authorization";
	String TOKEN_PREFIX  = "Bearer ";
	Long EXPIRATION_TIME = 8640000000l; //100 Days   
	String SECRET = "123456789abcde987654321";
	String JWT_AUTHORITIES_KEY  = "Authortity"; 
}
