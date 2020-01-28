package com.gestorc.domain.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.exception.EspecialidadeNotFoundException;
import com.gestorc.domain.exception.GrupoNotFoundException;
import com.gestorc.domain.exception.BusinessException;
import com.gestorc.domain.model.Especialidade;
import com.gestorc.domain.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	private static final String MSG_ESPEC_EM_USO = "Especialidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EspecialidadeRepository repository;

	public Especialidade isExists(Long id) {
		return repository.findById(id).orElseThrow(() -> new EspecialidadeNotFoundException(id));
	}

	@Transactional
	public Especialidade save(Especialidade obj) {
		repository.detach(obj);

		Optional<Especialidade> objSave = repository.findByNome(obj.getNome());

		if (objSave.isPresent() && !objSave.get().equals(obj)) {
			throw new BusinessException(
					String.format("Já existe uma especialidade cadastrada com o nome %s", obj.getNome()));
		}

		return repository.save(obj);
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException(String.format(MSG_ESPEC_EM_USO, id));
		}
	}
}
