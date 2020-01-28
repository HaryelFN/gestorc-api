package com.gestorc.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.assembler.EspecialidadeInputDisassembler;
import com.gestorc.api.v1.assembler.EspecialidadeModelAssembler;
import com.gestorc.api.v1.model.especialidade.EspecialidadeModel;
import com.gestorc.api.v1.model.input.EspecialidadeInput;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Especialidade;
import com.gestorc.domain.repository.EspecialidadeRepository;
import com.gestorc.domain.service.EspecialidadeService;

@RestController
@RequestMapping(path = "/v1/especialidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadeController {

	@Autowired
	private EspecialidadeRepository repository;

	@Autowired
	private EspecialidadeService service;

	@Autowired
	private EspecialidadeModelAssembler assembler;

	@Autowired
	private EspecialidadeInputDisassembler disassembler;

	@CheckSecurity.Especialidades.find
	@GetMapping
	public List<EspecialidadeModel> listar() {
		List<Especialidade> list = repository.findAll();
		return assembler.toCollectionModel(list);
	}

	@CheckSecurity.Especialidades.find
	@GetMapping("/{id}")
	public EspecialidadeModel getById(@PathVariable Long id) {
		Especialidade obj = service.isExists(id);
		return assembler.toModel(obj);
	}

	@CheckSecurity.Especialidades.add
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EspecialidadeModel save(@RequestBody @Valid EspecialidadeInput input) {
		Especialidade obj = disassembler.toDomainObject(input);
		obj = service.save(obj);
		return assembler.toModel(obj);
	}

	@CheckSecurity.Especialidades.add
	@PutMapping("/{id}")
	public EspecialidadeModel update(@PathVariable Long id, @RequestBody @Valid EspecialidadeInput input) {
		Especialidade objSave = service.isExists(id);
		disassembler.copyToDomainObject(input, objSave);
		objSave = service.save(objSave);
		return assembler.toModel(objSave);
	}

	@CheckSecurity.Especialidades.remove
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.delete(id);
	}

}
