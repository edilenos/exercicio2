package br.com.navita.patrimonymanagement.service;

import br.com.navita.patrimonymanagement.dto.request.BrandRequest;
import br.com.navita.patrimonymanagement.dto.response.BrandResponse;

public interface BrandService {
	
	BrandResponse create(BrandRequest request);
	BrandResponse update(BrandRequest request);
	BrandResponse getById(Long id);
	void deleteById(Long id);
	
}
