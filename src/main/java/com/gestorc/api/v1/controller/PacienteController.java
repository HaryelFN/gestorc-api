package com.gestorc.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.model.paciente.PacienteResumoModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.exception.BusinessException;
import com.gestorc.domain.exception.PessoaNotFoundException;
import com.gestorc.domain.model.Paciente;
import com.gestorc.domain.repository.PacienteRepository;
import com.gestorc.domain.service.PacienteService;

@RestController
@RequestMapping(path = "/v1/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@Autowired
	private PacienteService service;

	@CheckSecurity.Pacientes.find
	@GetMapping
	public List<Paciente> getAll() {
		return repository.findAll();
	}

	@CheckSecurity.Pacientes.find
	@GetMapping(params = "pageable")
	public Page<PacienteResumoModel> getResumoPageable(@RequestParam String nome, Pageable pageable) {
		return repository.getResumoPageable(nome,
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
	}

	@CheckSecurity.Pacientes.find
	@GetMapping("/{id}")
	public Paciente getByID(@PathVariable Long id) {
		return service.isExists(id);
	}

	@CheckSecurity.Pacientes.find
	@GetMapping("/cpf/{cpf}")
	public Paciente getByPessoaID(@PathVariable String cpf) {
		Optional<Paciente> opt = repository.findByPessoaCpf(cpf);
		return opt.isPresent() ? opt.get() : null;
	}

	@CheckSecurity.Pacientes.add
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente save(@RequestBody @Valid Paciente obj) {
		try {
			return service.save(obj);
		} catch (PessoaNotFoundException ex) {
			throw new BusinessException(ex.getMessage());
		}
	}

	@CheckSecurity.Pacientes.add
	@PutMapping("/{id}")
	public Paciente update(@PathVariable Long id, @RequestBody @Valid Paciente obj) {
		return service.update(id, obj);
	}
}
