package com.gestorc.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Prontuario;
import com.gestorc.domain.repository.ProntuarioRepository;

@RestController
@RequestMapping(path = "/v1/prontuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProntuarioController {

	@Autowired
	private ProntuarioRepository repository;
	
	@CheckSecurity.Medicos.find
	@GetMapping("/paciente/{id}")
	public List<Prontuario> getByID(@PathVariable Long id) {
		return repository.findByPacienteId(id);
	}
}
