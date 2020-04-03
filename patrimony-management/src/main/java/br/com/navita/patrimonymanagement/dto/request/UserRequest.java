package br.com.navita.patrimonymanagement.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRequest {
	
	private Long id;
	
	@NotNull(message = "{field.required}")
	@Size(min = 2, max = 60, message = "{field.invalid.size}")
	private String name;
	
	@NotNull(message = "{field.required}")
	@Email(message = "{field.invalid.email}")
	@Size(min = 2, max = 60, message = "{field.invalid.size}")
	private String email;
	
	@NotNull(message = "{field.required}")
	@Size(min = 2, max = 100, message = "{field.invalid.size}")
	private String password;

}
