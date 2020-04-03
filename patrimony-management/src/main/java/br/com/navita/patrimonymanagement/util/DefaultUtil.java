package br.com.navita.patrimonymanagement.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUtil {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Converte object de um tipo para outro
	 * 
	 * @param <D>
	 * @param objectFrom
	 * @param classeTo
	 * @return
	 */
	public <D> D convertTo(Object objectFrom, Class<D> classeTo) {
		return modelMapper.map(objectFrom, classeTo);
	}
	
}
