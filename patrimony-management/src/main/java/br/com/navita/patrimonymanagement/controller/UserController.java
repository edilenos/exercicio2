package br.com.navita.patrimonymanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.navita.patrimonymanagement.dto.request.UserRequest;
import br.com.navita.patrimonymanagement.dto.response.UserResponse;
import br.com.navita.patrimonymanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(value = "Usuário API")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@ApiOperation(value = "Cria um usuário")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
	}
	
	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request) {
		return ResponseEntity.ok(this.service.update(request));
	}
	
	@ApiOperation(value = "Recupera um usuário")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@ApiOperation(value = "Deleta um usuário")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
