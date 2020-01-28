package com.gestorc.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorc.domain.exception.PermissaoNotFoundException;
import com.gestorc.domain.model.Permissao;
import com.gestorc.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNotFoundException(permissaoId));
	}
	
}
