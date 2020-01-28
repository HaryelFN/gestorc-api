package com.gestorc.domain.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private char sexo;

	@Column(nullable = false, name = "dt_nascimento")
	private LocalDate dataNascimento;

	@Column(nullable = false)
	private String nome;

	@CPF
	@Column(nullable = true, unique = true)
	private String cpf;

	private String telefone;

	@Email
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", nullable = true)
	private Endereco endereco;

}