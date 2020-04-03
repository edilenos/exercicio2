package br.com.navita.patrimonymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.navita.patrimonymanagement.entity.BrandEntity;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {
	
	boolean existsByName(String name);
	boolean existsByNameAndIdNot(String name, Long id);
	
}
