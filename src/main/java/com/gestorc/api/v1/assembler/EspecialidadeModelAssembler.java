package com.gestorc.api.v1.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestorc.api.v1.model.especialidade.EspecialidadeModel;
import com.gestorc.domain.model.Especialidade;

@Component
public class EspecialidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EspecialidadeModel toModel(Especialidade obj) {
		return modelMapper.map(obj, EspecialidadeModel.class);
	}

	public List<EspecialidadeModel> toCollectionModel(Collection<Especialidade> list) {
		return list.stream().map(permissao -> toModel(permissao)).collect(Collectors.toList());
	}
}
