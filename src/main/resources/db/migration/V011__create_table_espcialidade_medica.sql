CREATE TABLE especialidade_medica (
	id_especialidade BIGINT(20) NOT NULL,
	id_medico BIGINT(20) NOT NULL,
	FOREIGN KEY (id_especialidade) REFERENCES especialidade (id),
	FOREIGN KEY (id_medico) REFERENCES medico (id),
	PRIMARY KEY (id_especialidade, id_medico)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;