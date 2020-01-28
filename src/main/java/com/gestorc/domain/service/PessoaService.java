package com.gestorc.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.exception.PessoaNotFoundException;
import com.gestorc.domain.model.Endereco;
import com.gestorc.domain.model.Pessoa;
import com.gestorc.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private EnderecoService endService;

	@Transactional
	public Pessoa save(Pessoa obj) {
		repository.detach(obj);

		Endereco endSave = endService.save(obj.getEndereco());
		obj.setEndereco(endSave);

		return repository.save(obj);
	}

	@Transactional
	public Pessoa update(Pessoa obj) {
		repository.detach(obj);

		Endereco endSave = endService.save(obj.getEndereco());
		obj.setEndereco(endSave);

		Pessoa objSave = isExists(obj.getId());

		BeanUtils.copyProperties(obj, objSave, "id", "cpf");
		return repository.save(obj);
	}

	public Pessoa isExists(Long id) {
		return repository.findById(id).orElseThrow(() -> new PessoaNotFoundException(id));
	}
}
