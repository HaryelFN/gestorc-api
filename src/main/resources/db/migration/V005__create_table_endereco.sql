CREATE TABLE endereco (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	bairro VARCHAR(200) NOT NULL,
	logradouro VARCHAR(255) NOT NULL,
	numero VARCHAR(100),
	complemento VARCHAR(255),
	id_cep BIGINT(20) NOT NULL,
	FOREIGN KEY (id_cep) REFERENCES cep (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;