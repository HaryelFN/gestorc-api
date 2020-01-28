package com.gestorc.domain.exception;

public class GrupoNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public GrupoNotFoundException(String mensagem) {
		super(mensagem);
	}

	public GrupoNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de grupo com código %d", id));
	}

}
