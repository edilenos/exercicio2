package br.com.navita.patrimonymanagement.exceptions.handlers;

import org.springframework.http.HttpStatus;

import br.com.navita.patrimonymanagement.enums.MessageResponseEnum;
import lombok.Data;

@Data
public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
    private final String message;
    
	public ResponseException(MessageResponseEnum messageResponseEnum) {
		this.httpStatus = messageResponseEnum.getHttpStatus();
		this.message = messageResponseEnum.getMessage();
	}
	
}
