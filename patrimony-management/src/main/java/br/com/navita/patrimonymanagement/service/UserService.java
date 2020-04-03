package br.com.navita.patrimonymanagement.service;

import br.com.navita.patrimonymanagement.dto.request.UserRequest;
import br.com.navita.patrimonymanagement.dto.response.UserResponse;

public interface UserService {

	UserResponse create(UserRequest request);
	UserResponse update(UserRequest request);
	UserResponse getById(Long id);
	void deleteById(Long id);
}
