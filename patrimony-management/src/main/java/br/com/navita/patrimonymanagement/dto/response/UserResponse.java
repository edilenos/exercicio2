package br.com.navita.patrimonymanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
}
