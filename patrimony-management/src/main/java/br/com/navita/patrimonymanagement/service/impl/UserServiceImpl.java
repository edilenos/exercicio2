package br.com.navita.patrimonymanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.navita.patrimonymanagement.dto.request.UserRequest;
import br.com.navita.patrimonymanagement.dto.response.UserResponse;
import br.com.navita.patrimonymanagement.entity.UserEntity;
import br.com.navita.patrimonymanagement.enums.MessageResponseEnum;
import br.com.navita.patrimonymanagement.exceptions.handlers.ResponseException;
import br.com.navita.patrimonymanagement.repository.UserJpaRepository;
import br.com.navita.patrimonymanagement.service.UserService;
import br.com.navita.patrimonymanagement.util.DefaultUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserJpaRepository repository;

	@Autowired
	private DefaultUtil util;
	
	@Override
	public UserResponse create(UserRequest request) {
		log.info("Criando usuário: {}", request);
		
		// ID será gerado automaticamente
		request.setId(null);
		
		// Não permitir duplicidade de e-mail
		if(this.repository.existsByEmail(request.getEmail()))
			throw new ResponseException(MessageResponseEnum.EMAIL_IS_ALREADY_IN_USE);
		
		// converte request para entity
		UserEntity entity = this.util.convertTo(request, UserEntity.class);
		
		// encode da senha do usuario
		entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		
		// salva e converte para response
		return this.util.convertTo(this.repository.save(entity), UserResponse.class);
	}

	@Override
	public UserResponse update(UserRequest request) {
		log.info("Atualizando usuário: {}", request);
		
		// ID é obrigatório na atualizacao
		if(request.getId() == null)
			throw new ResponseException(MessageResponseEnum.FIELD_ID_REQUIRED);
		
		// Verifica existencia do usuário
		if(!this.repository.existsById(request.getId()))
			throw new ResponseException(MessageResponseEnum.USER_NOT_FOUND);
		
		// Não permitir duplicidade de e-mail
		if(this.repository.existsByEmailAndIdNot(request.getEmail(), request.getId()))
			throw new ResponseException(MessageResponseEnum.EMAIL_IS_ALREADY_IN_USE);
		
		// converte request para entity
		UserEntity entity = this.util.convertTo(request, UserEntity.class);
		
		// salva e converte para response
		return this.util.convertTo(this.repository.save(entity), UserResponse.class);
	}

	@Override
	public UserResponse getById(Long id) {
		// realiza a busca no banco
		Optional<UserEntity> entity = this.repository.findById(id);
		
		// se não existir, lança erro
		if(!entity.isPresent())
			throw new ResponseException(MessageResponseEnum.USER_NOT_FOUND);
		
		// converte para response
		return this.util.convertTo(entity.get(), UserResponse.class);
	}
	
	@Override
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}
	
}
