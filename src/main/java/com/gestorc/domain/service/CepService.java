package com.gestorc.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.CepRepository;
import com.gestorc.domain.model.Cep;

@Service
public class CepService {

	@Autowired
	private CepRepository repository;

	@Transactional
	public Cep save(Cep obj) {
		repository.detach(obj);
		Optional<Cep> opt = repository.findByNumero(obj.getNumero());
		return opt.isPresent() ? opt.get() : repository.save(obj);
	}

}
