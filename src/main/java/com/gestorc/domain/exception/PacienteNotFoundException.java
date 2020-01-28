package com.gestorc.domain.exception;

public class PacienteNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PacienteNotFoundException(String mensagem) {
		super(mensagem);
	}

	public PacienteNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de paciente com código %d", id));
	}

}
