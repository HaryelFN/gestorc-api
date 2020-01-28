package com.gestorc.domain.repository;

import org.springframework.stereotype.Repository;

import com.gestorc.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {

}
