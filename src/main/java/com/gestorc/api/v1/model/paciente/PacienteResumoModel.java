package com.gestorc.api.v1.model.paciente;

import java.time.LocalDate;

public interface PacienteResumoModel {

	public Long getId();

	public String getCpf();

	public String getNome();
	
	public LocalDate getDataNascimento();

	public String getTelefone();
}
