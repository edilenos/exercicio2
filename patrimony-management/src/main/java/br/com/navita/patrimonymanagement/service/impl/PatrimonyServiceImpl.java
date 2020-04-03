package br.com.navita.patrimonymanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.navita.patrimonymanagement.dto.request.PatrimonyRequest;
import br.com.navita.patrimonymanagement.dto.response.PatrimonyResponse;
import br.com.navita.patrimonymanagement.entity.BrandEntity;
import br.com.navita.patrimonymanagement.entity.PatrimonyEntity;
import br.com.navita.patrimonymanagement.enums.MessageResponseEnum;
import br.com.navita.patrimonymanagement.exceptions.handlers.ResponseException;
import br.com.navita.patrimonymanagement.repository.BrandJpaRepository;
import br.com.navita.patrimonymanagement.repository.PatrimonyJpaRepository;
import br.com.navita.patrimonymanagement.service.PatrimonyService;
import br.com.navita.patrimonymanagement.util.DefaultUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatrimonyServiceImpl implements PatrimonyService {

	@Autowired
	private PatrimonyJpaRepository patrimonyJpaRepository;
	
	@Autowired
	private BrandJpaRepository brandJpaRepository;
	
	@Autowired
	private DefaultUtil util;
	
	@Override
	public PatrimonyResponse create(PatrimonyRequest request) {
		log.info("Criando patrimonio: {}", request);
		
		// ID será gerado automaticamente
		request.setId(null);
		
		// Brand ID é obrigatório na atualizacao
		if(request.getBrand() == null || request.getBrand().getId() == null)
			throw new ResponseException(MessageResponseEnum.FIELD_BRAND_ID_REQUIRED);

		// realiza a busca da marca no banco
		Optional<BrandEntity> brandEntity = this.brandJpaRepository.findById(request.getBrand().getId());
		
		// se não existir, lança erro
		if(!brandEntity.isPresent())
			throw new ResponseException(MessageResponseEnum.BRAND_NOT_FOUND);
		
		// converte request para entity
		PatrimonyEntity patrimonyEntity = this.util.convertTo(request, PatrimonyEntity.class);
		
		// seta a marca
		patrimonyEntity.setBrand(brandEntity.get());
		
		// salva e converte para response
		return this.util.convertTo(this.patrimonyJpaRepository.save(patrimonyEntity), PatrimonyResponse.class);
	}

	@Override
	public PatrimonyResponse update(PatrimonyRequest request) {
		log.info("Atualizando patrimonio: {}", request);
		
		// ID é obrigatório na atualizacao
		if(request.getId() == null)
			throw new ResponseException(MessageResponseEnum.FIELD_ID_REQUIRED);
		
		// Brand ID é obrigatório na atualizacao
		if(request.getBrand() == null || request.getBrand().getId() == null)
			throw new ResponseException(MessageResponseEnum.FIELD_BRAND_ID_REQUIRED);
		
		// Verifica existencia do patrimonio
		if(!this.patrimonyJpaRepository.existsById(request.getId()))
			throw new ResponseException(MessageResponseEnum.PATRIMONY_NOT_FOUND);
		
		// realiza a busca da marca no banco
		Optional<BrandEntity> brandEntity = this.brandJpaRepository.findById(request.getBrand().getId());
		
		// se não existir, lança erro
		if(!brandEntity.isPresent())
			throw new ResponseException(MessageResponseEnum.BRAND_NOT_FOUND);
		
		// converte request para entity
		PatrimonyEntity patrimonyEntity = this.util.convertTo(request, PatrimonyEntity.class);
		
		// seta a marca
		patrimonyEntity.setBrand(brandEntity.get());
		
		// salva e converte para response
		return this.util.convertTo(this.patrimonyJpaRepository.save(patrimonyEntity), PatrimonyResponse.class);
	}

	@Override
	public PatrimonyResponse getById(Long id) {
		
		// realiza a busca no banco
		Optional<PatrimonyEntity> entity = this.patrimonyJpaRepository.findById(id);
		
		// se não existir, lança erro
		if(!entity.isPresent())
			throw new ResponseException(MessageResponseEnum.PATRIMONY_NOT_FOUND);
		
		// converte para response
		return this.util.convertTo(entity.get(), PatrimonyResponse.class);
	}

	@Override
	public void deleteById(Long id) {
		this.patrimonyJpaRepository.deleteById(id);
	}

}
