package br.com.navita.patrimonymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.navita.patrimonymanagement.entity.PatrimonyEntity;

public interface ClienteJpaRepository extends JpaRepository<PatrimonyEntity, Long> {}
