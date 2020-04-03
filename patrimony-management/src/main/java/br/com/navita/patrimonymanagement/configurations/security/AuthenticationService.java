package br.com.navita.patrimonymanagement.configurations.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.navita.patrimonymanagement.dto.request.AuthenticationRequest;
import br.com.navita.patrimonymanagement.dto.response.TokenResponse;
import br.com.navita.patrimonymanagement.entity.UserEntity;
import br.com.navita.patrimonymanagement.enums.MessageResponseEnum;
import br.com.navita.patrimonymanagement.exceptions.handlers.ResponseException;
import br.com.navita.patrimonymanagement.repository.UserJpaRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserJpaRepository repository;
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = repository.findByEmail(username);
		if (user.isPresent())
			return user.get();
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}
	
	public TokenResponse authenticate(AuthenticationRequest request) {
		
		UsernamePasswordAuthenticationToken dataLogin = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		
		try {
			Authentication authentication = authManager.authenticate(dataLogin);
			String token = tokenService.gerarToken(authentication);
			return new TokenResponse(token, "Bearer");
		} catch (AuthenticationException e) {
			throw new ResponseException(MessageResponseEnum.ERROR_AUTHETICATION);
		}

	}

}