package com.gestorc.domain.exception;

public class EspecialidadeNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EspecialidadeNotFoundException(String mensagem) {
		super(mensagem);
	}

	public EspecialidadeNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de especialidade com código %d", id));
	}

}
