package br.com.navita.patrimonymanagement.service;

import br.com.navita.patrimonymanagement.dto.request.PatrimonyRequest;
import br.com.navita.patrimonymanagement.dto.response.PatrimonyResponse;

public interface PatrimonyService {
	
	PatrimonyResponse create(PatrimonyRequest request);
	PatrimonyResponse update(PatrimonyRequest request);
	PatrimonyResponse getById(Long id);
	void deleteById(Long id);
}
