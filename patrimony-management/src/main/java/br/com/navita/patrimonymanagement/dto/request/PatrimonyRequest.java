package br.com.navita.patrimonymanagement.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PatrimonyRequest {
	
	private Long id;
	
	@NotNull(message = "{field.required}")
	@Size(min = 2, max = 60, message = "{field.invalid.size}")
	private String name;
	
	@Size(min = 2, max = 300, message = "{field.invalid.size}")
	private String description;
	
	@NotNull(message = "{field.required}")
	private BrandRequest brand;
	
}
