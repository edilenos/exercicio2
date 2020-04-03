package br.com.navita.patrimonymanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.navita.patrimonymanagement.configurations.security.AuthenticationService;
import br.com.navita.patrimonymanagement.dto.request.AuthenticationRequest;
import br.com.navita.patrimonymanagement.dto.response.TokenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(value = "Autenticação API")
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@ApiOperation(value = "Cria um token")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.authenticate(request));
	}
	
}
