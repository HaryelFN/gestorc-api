package com.gestorc.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Medico {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean ativo;

	@Column(name = "uf_crm", nullable = false)
	private String ufCrm;

	@Column(nullable = false, unique = true)
	private String crm;

	@ManyToMany
	@JoinTable(name = "especialidade_medica", joinColumns = @JoinColumn(name = "id_especialidade"), inverseJoinColumns = @JoinColumn(name = "id_medico"))
	private List<Especialidade> especialidades = new ArrayList<>();

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false, name = "id_pessoa")
	private Pessoa pessoa;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "dt_criacao", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "dt_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

	public boolean removeEspecialidade(Especialidade obj) {
		return getEspecialidades().remove(obj);
	}

	public boolean addEspecialidade(Especialidade obj) {
		return getEspecialidades().add(obj);
	}
}
