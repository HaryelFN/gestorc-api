package com.gestorc.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gestorc.domain.model.Especialidade;

@Repository
public interface EspecialidadeRepository extends CustomJpaRepository<Especialidade, Long> {

	Optional<Especialidade> findByNome(String nome);
}
