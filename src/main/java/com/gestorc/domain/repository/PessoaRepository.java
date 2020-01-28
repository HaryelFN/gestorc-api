package com.gestorc.domain.repository;

import java.util.Optional;

import com.gestorc.domain.model.Pessoa;

public interface PessoaRepository extends CustomJpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByCpf(String cpf);
}
