CREATE TABLE grupo_permissao (
	grupo_id BIGINT(20) NOT NULL,
	permissao_id BIGINT(20) NOT NULL,
	FOREIGN KEY (grupo_id) REFERENCES grupo (id),
	FOREIGN KEY (permissao_id) REFERENCES permissao (id),
	PRIMARY KEY (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8;