package com.gestorc.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestorc.api.v1.model.paciente.PacienteResumoModel;
import com.gestorc.domain.model.Paciente;

public interface PacienteRepository extends CustomJpaRepository<Paciente, Long>{
	
	public Optional<Paciente> findByPessoaCpf(String cpf);
	
	@Query(value = "SELECT pac.id, pes.cpf, pes.nome, pes.dt_nascimento AS dataNascimento, pes.telefone FROM pessoa pes INNER JOIN paciente pac ON pes.id = pac.id_pessoa WHERE LOWER(pes.nome) LIKE LOWER(CONCAT('%', :nome, '%'))", 
			countQuery = "SELECT COUNT(*) FROM pessoa pes INNER JOIN paciente pac ON pes.id = pac.id_pessoa WHERE LOWER(pes.nome) LIKE LOWER(CONCAT('%', :nome, '%'))", 
			nativeQuery = true)
	public Page<PacienteResumoModel> getResumoPageable(@Param("nome") String nome, Pageable pageable);
}
