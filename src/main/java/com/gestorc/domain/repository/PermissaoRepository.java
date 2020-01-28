package com.gestorc.domain.repository;

import org.springframework.stereotype.Repository;

import com.gestorc.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
