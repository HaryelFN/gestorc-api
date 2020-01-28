package com.gestorc.domain.repository;

import java.util.List;

import com.gestorc.domain.model.Prontuario;

public interface ProntuarioRepository extends CustomJpaRepository<Prontuario, Long> {

	public List<Prontuario> findByPacienteId(Long id);
}
