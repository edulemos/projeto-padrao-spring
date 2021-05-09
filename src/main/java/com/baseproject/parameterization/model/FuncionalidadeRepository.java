package com.baseproject.parameterization.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long> {

	Funcionalidade findByNome(String nome);

	Optional<Funcionalidade> findByUuid(String uuid);

	List<Funcionalidade> findByNomeContainingIgnoreCase(String name, Pageable pageable);


}
