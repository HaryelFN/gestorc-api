package com.gestorc.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestorc.api.v1.model.medico.MedicoResumoModel;
import com.gestorc.domain.model.Medico;

@Repository
public interface MedicoRepository extends CustomJpaRepository<Medico, Long> {
	
	@Query(value = "SELECT m.id, CONCAT(m.crm, '/', m.uf_crm) AS crm, p.nome, p.telefone FROM pessoa p INNER JOIN medico m ON m.id_pessoa = p.id WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))", 
			countQuery = "SELECT COUNT(*) FROM pessoa p INNER JOIN medico m ON m.id_pessoa = p.id WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))", 
			nativeQuery = true)
	public Page<MedicoResumoModel> getResumoPageable(@Param("nome") String nome, Pageable pageable);
	
}
