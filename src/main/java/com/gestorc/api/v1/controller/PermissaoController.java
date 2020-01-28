package com.gestorc.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.assembler.PermissaoModelAssembler;
import com.gestorc.api.v1.model.permissao.PermissaoModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Permissao;
import com.gestorc.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public List<PermissaoModel> listar() {
		List<Permissao> todasPermissoes = permissaoRepository.findAll();
		return permissaoModelAssembler.toCollectionModel(todasPermissoes);
	}
	
}
