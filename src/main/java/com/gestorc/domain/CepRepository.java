package com.gestorc.domain;

import java.util.Optional;

import com.gestorc.domain.model.Cep;
import com.gestorc.domain.repository.CustomJpaRepository;

public interface CepRepository extends CustomJpaRepository<Cep, Long> {

	Optional<Cep> findByNumero(String numero);

}
