package br.com.navita.patrimonymanagement.enums;

import org.springframework.http.HttpStatus;

public enum MessageResponseEnum {
	
	// BAD REQUEST
	NAME_IS_ALREADY_IN_USE(HttpStatus.BAD_REQUEST, "name.is.already.in.use"),
	EMAIL_IS_ALREADY_IN_USE(HttpStatus.BAD_REQUEST, "email.is.already.in.use"),
	FIELD_ID_REQUIRED(HttpStatus.BAD_REQUEST, "field.id.required"),
	FIELD_BRAND_ID_REQUIRED(HttpStatus.BAD_REQUEST, "brand.id.not.found"),
	ERROR_AUTHETICATION(HttpStatus.BAD_REQUEST,"error.authetication"),

	// NOT FOUND
	BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "brand.not.found"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user.not.found"),
	PATRIMONY_NOT_FOUND(HttpStatus.NOT_FOUND, "patrimony.not.found");
	
	private MessageResponseEnum(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	private HttpStatus httpStatus;
	private String message;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
