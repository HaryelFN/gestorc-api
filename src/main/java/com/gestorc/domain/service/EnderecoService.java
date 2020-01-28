package com.gestorc.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.exception.EnderecoNotFoundException;
import com.gestorc.domain.model.Cep;
import com.gestorc.domain.model.Endereco;
import com.gestorc.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private CepService cepService;

	@Transactional
	public Endereco save(Endereco obj) {
		repository.detach(obj);

		Cep cepSave = cepService.save(obj.getCep());
		obj.setCep(cepSave);

		return repository.save(obj);
	}
	
	@Transactional
	public Endereco update(Endereco obj) {
		repository.detach(obj);

		Cep cepSave = cepService.save(obj.getCep());
		obj.setCep(cepSave);
		
		Endereco objSave = isExists(obj.getId());
		
		BeanUtils.copyProperties(obj, objSave, "id");
		return repository.save(obj);
	}
	
	public Endereco isExists(Long id) {
		return repository.findById(id).orElseThrow(() -> new EnderecoNotFoundException(id));
	}
}
