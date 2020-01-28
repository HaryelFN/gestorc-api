package com.gestorc.domain.exception;

public class PermissaoNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PermissaoNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de permissão com código %d", id));
	}
	
}
