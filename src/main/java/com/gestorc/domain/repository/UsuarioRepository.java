package com.gestorc.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gestorc.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
	
}
