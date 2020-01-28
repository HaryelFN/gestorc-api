package com.gestorc.domain.exception;

public class UsuarioNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de usuário com código %d", id));
	}

}
