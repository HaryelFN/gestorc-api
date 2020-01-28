package com.gestorc.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestorc.api.v1.model.input.EspecialidadeInput;
import com.gestorc.domain.model.Especialidade;

@Component
public class EspecialidadeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Especialidade toDomainObject(EspecialidadeInput input) {
		return modelMapper.map(input, Especialidade.class);
	}

	public void copyToDomainObject(EspecialidadeInput input, Especialidade obj) {
		modelMapper.map(input, obj);
	}
}
