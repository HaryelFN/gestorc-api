package com.gestorc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Pessoa;
import com.gestorc.domain.repository.PessoaRepository;

@RestController
@RequestMapping(path = "/v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@CheckSecurity.Pacientes.find
	@GetMapping("/exists")
	public Pessoa getByID(@RequestParam String cpf) {
		return repository.findByCpf(cpf).orElse(null);
	}
}
