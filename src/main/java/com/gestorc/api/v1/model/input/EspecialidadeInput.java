package com.gestorc.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EspecialidadeInput {

	private Long id;
	
	@NotBlank
	private String nome;

}
