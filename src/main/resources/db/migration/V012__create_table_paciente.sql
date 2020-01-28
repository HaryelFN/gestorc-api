CREATE TABLE paciente (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_pessoa BIGINT(20) NOT NULL,
    dt_criacao DATETIME NOT NULL,
    dt_atualizacao DATETIME,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;