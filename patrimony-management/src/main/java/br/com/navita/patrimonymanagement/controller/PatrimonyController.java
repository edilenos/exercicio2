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

import br.com.navita.patrimonymanagement.dto.request.PatrimonyRequest;
import br.com.navita.patrimonymanagement.dto.response.PatrimonyResponse;
import br.com.navita.patrimonymanagement.service.PatrimonyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(value = "Patrimonio API")
@RequestMapping("/patrimony")
public class PatrimonyController {

	@Autowired
	private PatrimonyService service;

	@ApiOperation(value = "Cria um patrimônio")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatrimonyResponse> create(@RequestBody @Valid PatrimonyRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
	}

	@ApiOperation(value = "Atualiza um patrimônio")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatrimonyResponse> update(@RequestBody @Valid PatrimonyRequest request) {
		return ResponseEntity.ok(this.service.update(request));
	}

	@ApiOperation(value = "Recupera um patrimônio")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatrimonyResponse> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@ApiOperation(value = "Deleta um patrimônio")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
