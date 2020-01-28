package com.gestorc.domain.exception;

public class PessoaNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(String mensagem) {
		super(mensagem);
	}

	public PessoaNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de pessoa com código %d", id));
	}

}
