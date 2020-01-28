package com.gestorc.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Prontuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private int numero;
	
	@JsonIgnore
	@OneToOne(optional = false)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;
}
