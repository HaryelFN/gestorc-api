CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(100) NOT NULL,
	objeto VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;