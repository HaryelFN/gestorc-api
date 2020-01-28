package com.gestorc.domain.exception;

public class EnderecoNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EnderecoNotFoundException(String mensagem) {
		super(mensagem);
	}

	public EnderecoNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de endereço com código %d", id));
	}

}
