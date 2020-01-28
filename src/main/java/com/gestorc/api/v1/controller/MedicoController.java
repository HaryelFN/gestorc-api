package com.gestorc.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.model.medico.MedicoResumoModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Medico;
import com.gestorc.domain.repository.MedicoRepository;

@RestController
@RequestMapping(path = "/v1/medicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;

	@CheckSecurity.Medicos.find
	@GetMapping
	public List<Medico> getAll() {
		return repository.findAll();
	}
	
	@CheckSecurity.Medicos.find
	@GetMapping(params = "pageable")
	public Page<MedicoResumoModel> getResumoPageable(@RequestParam String nome, Pageable pageable) {
		return repository.getResumoPageable(nome, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
	}
	
	@CheckSecurity.Medicos.find
	@GetMapping("/{id}")
	public ResponseEntity<Medico> getByID(@PathVariable Long id) {
		Optional<Medico> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}
	
//	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
//	@Override
//	@GetMapping("/{grupoId}")
//	public GrupoModel buscar(@PathVariable Long grupoId) {
//		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
//		
//		return grupoModelAssembler.toModel(grupo);
//	}
}
