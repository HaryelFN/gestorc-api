package com.gestorc.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.exception.PacienteNotFoundException;
import com.gestorc.domain.model.Paciente;
import com.gestorc.domain.model.Pessoa;
import com.gestorc.domain.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	@Autowired
	private PessoaService pesService;

	@Transactional
	public Paciente save(Paciente obj) {
		repository.detach(obj);

		Pessoa pesSave = pesService.save(obj.getPessoa());
		obj.setPessoa(pesSave);

		return repository.save(obj);
	}

	@Transactional
	public Paciente update(Long id, Paciente obj) {
		repository.detach(obj);

		Pessoa pesSave = pesService.save(obj.getPessoa());
		obj.setPessoa(pesSave);

		Paciente objSave = isExists(obj.getId());

		BeanUtils.copyProperties(obj, objSave, "id", "dataCadastro", "dataAtualizacao");
		return repository.save(obj);
	}

	public Paciente isExists(Long id) {
		return repository.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
	}
}
