  CREATE TABLE usuario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(300) NOT NULL,
    ativo TINYINT(1) NOT NULL,
    id_pessoa BIGINT(20) NOT NULL,
    dt_criacao DATETIME NOT NULL,
    dt_atualizacao DATETIME,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;