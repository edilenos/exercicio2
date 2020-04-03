package br.com.navita.patrimonymanagement.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.navita.patrimonymanagement.dto.response.Response;
import br.com.navita.patrimonymanagement.exceptions.handlers.ResponseException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

	private static final String UNEXPECTED_ERROR_HAPPENED = "Erro inesperado do sistema";

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Handler response exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseBody
	@ExceptionHandler(ResponseException.class)
	ResponseEntity<Response> handlerResponseException(ResponseException ex) {
		log.error(UNEXPECTED_ERROR_HAPPENED, ex);
		
		// recupera a mensagem no message.properties
		String fullMessage = messageSource.getMessage(ex.getMessage(), null, null);
		
		return ResponseEntity.status(ex.getHttpStatus()).body(new Response(fullMessage));
	}

	/**
	 * Handler not verified exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<Response> handlerNotVerifiedException(RuntimeException ex) {
		log.error(UNEXPECTED_ERROR_HAPPENED, ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(UNEXPECTED_ERROR_HAPPENED));
	}
}
