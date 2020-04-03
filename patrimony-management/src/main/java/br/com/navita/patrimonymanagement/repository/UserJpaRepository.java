package br.com.navita.patrimonymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.navita.patrimonymanagement.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByEmailAndIdNot(String email, Long id);
	
}
