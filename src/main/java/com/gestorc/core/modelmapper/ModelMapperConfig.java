package com.gestorc.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Usuario.class, UsuarioModel.class)
//			.addMapping(Usuario::getPessoa, UsuarioModel::setLogin);

//		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
//				Endereco.class, EnderecoModel.class);

//		enderecoToEnderecoModelTypeMap.<String>addMapping(
//				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
//				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		return modelMapper;
	}

}
