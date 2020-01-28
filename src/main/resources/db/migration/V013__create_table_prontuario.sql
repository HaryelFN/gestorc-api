CREATE TABLE prontuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    numero INT(11) NOT NULL UNIQUE,
    id_paciente BIGINT(20) NOT NULL,
    id_especialidade BIGINT(20) NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES paciente (id),
    FOREIGN KEY (id_especialidade) REFERENCES especialidade (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;