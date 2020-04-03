package br.com.navita.patrimonymanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.navita.patrimonymanagement.dto.request.BrandRequest;
import br.com.navita.patrimonymanagement.dto.response.BrandResponse;
import br.com.navita.patrimonymanagement.entity.BrandEntity;
import br.com.navita.patrimonymanagement.enums.MessageResponseEnum;
import br.com.navita.patrimonymanagement.exceptions.handlers.ResponseException;
import br.com.navita.patrimonymanagement.repository.BrandJpaRepository;
import br.com.navita.patrimonymanagement.service.BrandService;
import br.com.navita.patrimonymanagement.util.DefaultUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandJpaRepository repository;

	@Autowired
	private DefaultUtil util;

	@Override
	public BrandResponse create(BrandRequest request) {
		log.info("Criando marca: {}", request);
		
		// ID será gerado automaticamente
		request.setId(null);
		
		// Não permitir mais de uma marca com o mesmo nome
		if(this.repository.existsByName(request.getName()))
			throw new ResponseException(MessageResponseEnum.NAME_IS_ALREADY_IN_USE);
		
		// converte request para entity
		BrandEntity entity = this.util.convertTo(request, BrandEntity.class);
		
		// salva e converte para response
		return this.util.convertTo(this.repository.save(entity), BrandResponse.class);
	}

	@Override
	public BrandResponse update(BrandRequest request) {
		log.info("Atualizando marca: {}", request);
		
		// ID é obrigatório na atualizacao
		if(request.getId() == null)
			throw new ResponseException(MessageResponseEnum.FIELD_ID_REQUIRED);
		
		// Verifica existencia da marca
		if(!this.repository.existsById(request.getId()))
			throw new ResponseException(MessageResponseEnum.BRAND_NOT_FOUND);
		
		// Não permitir mais de uma marca com o mesmo nome
		if(this.repository.existsByNameAndIdNot(request.getName(), request.getId()))
			throw new ResponseException(MessageResponseEnum.NAME_IS_ALREADY_IN_USE);
		
		// converte request para entity
		BrandEntity entity = this.util.convertTo(request, BrandEntity.class);
		
		// salva e converte para response
		return this.util.convertTo(this.repository.save(entity), BrandResponse.class);
	}

	@Override
	public BrandResponse getById(Long id) {
		
		// realiza a busca no banco
		Optional<BrandEntity> entity = this.repository.findById(id);
		
		// se não existir, lança erro
		if(!entity.isPresent())
			throw new ResponseException(MessageResponseEnum.BRAND_NOT_FOUND);
		
		// converte para response
		return this.util.convertTo(entity.get(), BrandResponse.class);
	}

	@Override
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
