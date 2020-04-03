package br.com.navita.patrimonymanagement.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthenticationRequest {
	
	@NotNull(message = "{field.required}")
	@Size(min = 2, max = 60, message = "{field.invalid.size}")
	private String email;
	
	@NotNull(message = "{field.required}")
	@Size(min = 2, max = 20, message = "{field.invalid.size}")
	private String password;

}
