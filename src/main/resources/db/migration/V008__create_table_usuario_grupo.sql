CREATE TABLE usuario_grupo (
	usuario_id BIGINT(20) NOT NULL,
	grupo_id BIGINT(20) NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES usuario (id),
	FOREIGN KEY (grupo_id) REFERENCES grupo (id),
    PRIMARY KEY (usuario_id, grupo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;