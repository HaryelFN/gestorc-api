SET foreign_key_checks = 0;

DELETE FROM permissao;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM cep;
DELETE FROM endereco;
DELETE FROM pessoa;
DELETE FROM usuario;
DELETE FROM usuario_grupo;
DELETE FROM especialidade;
DELETE FROM medico;
DELETE FROM especialidade_medica;
DELETE FROM paciente;
DELETE FROM prontuario;
DELETE FROM oauth_client_details;

SET foreign_key_checks = 1;

 -- RESET AUTO INCREMENT
ALTER TABLE permissao AUTO_INCREMENT = 1;
ALTER TABLE grupo AUTO_INCREMENT = 1;
ALTER TABLE cep AUTO_INCREMENT = 1;
ALTER TABLE endereco AUTO_INCREMENT = 1;
ALTER TABLE pessoa AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE especialidade AUTO_INCREMENT = 1;
ALTER TABLE medico AUTO_INCREMENT = 1;
ALTER TABLE paciente AUTO_INCREMENT = 1;
ALTER TABLE prontuario AUTO_INCREMENT = 1;

-- PERMISSAO
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (1, 'FIND_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões', 'PERMISSAO');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (2, 'EDIT_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões', 'PERMISSAO');

INSERT INTO permissao (id, nome, descricao, objeto) VALUES (3, 'ROLE_FIND_ESPECIALIDADE', 'Permite consultar especialidades', 'ESPECIALIDADE');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (4, 'ROLE_ADD_ESPECIALIDADE', 'Permite criar ou editar especialidades', 'ESPECIALIDADE');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (5, 'ROLE_REMOVE_ESPECIALIDADE', 'Permite remover especialidades', 'ESPECIALIDADE');

INSERT INTO permissao (id, nome, descricao, objeto) VALUES (6, 'ROLE_FIND_MEDICO', 'Permite consultar médicos', 'MEDICO');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (7, 'ROLE_ADD_MEDICO', 'Permite criar ou editar médicos', 'MEDICO');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (8, 'ROLE_REMOVE_MEDICO', 'Permite remover médidos', 'MEDICO');

INSERT INTO permissao (id, nome, descricao, objeto) VALUES (9, 'ROLE_FIND_PACIENTE', 'Permite consultar pacientes', 'PACIENTE');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (10, 'ROLE_ADD_PACIENTE', 'Permite criar ou editar pacientes', 'PACIENTE');
INSERT INTO permissao (id, nome, descricao, objeto) VALUES (11, 'ROLE_REMOVE_PACIENTE', 'Permite remover pacientes', 'PACIENTE');

-- GRUPO
INSERT INTO grupo (id, nome) VALUES (1, 'Root');

-- PERMISSAO ROOT
INSERT INTO grupo_permissao (grupo_id, permissao_id) SELECT 1, id FROM permissao;

-- CEP
INSERT INTO cep (id, numero, uf, cidade) VALUES (1, '75460000', 'GO', 'Nerópolis');

-- ENDEREÇO
INSERT INTO endereco (id, bairro, logradouro, numero, complemento, id_cep) VALUES (1, 'Setor São Paulo', 'Rua Tiradentes Q18 L25', null, null, 1);

-- PESSOA
INSERT INTO pessoa (id, sexo, dt_nascimento, nome, cpf, telefone, email, id_endereco) 
VALUES (1, 'M', '2019-05-22', 'Haryel Fernandes Nascimento', '75033623168', '62984098147', 'haryel@gmail.com', 1);

-- USUARIO
INSERT INTO usuario (id, login, senha, ativo, id_pessoa, dt_criacao, dt_atualizacao) VALUES
(1, 'admin', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', true, 1, (SELECT NOW()), null);

-- GRUPO USUARIO
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);

-- oauth_client_details
INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'algafood-web', null, '$2y$12$w3igMjsfS5XoAYuowoH3C.54vRFWlcXSHLjX7MwF990Kc2KKKh72e',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'foodanalytics', null, '$2y$12$fahbH37S2pyk1RPuIHKP.earzFmgAJJGo26rE.59vf4wwiiTKHnzO',
  'READ,WRITE', 'authorization_code', 'http://www.foodanalytics.local:8082', null,
  null, null, null
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'faturamento', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu',
  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS',
  null, null, null
);

-- ESPECIALIDADE MEDICA
INSERT INTO especialidade (id, nome) VALUES (1, 'Cardiologia');
INSERT INTO especialidade (id, nome) VALUES (2, 'Dermatologia');
INSERT INTO especialidade (id, nome) VALUES (3, 'Neurologia');
INSERT INTO especialidade (id, nome) VALUES (4, 'Oftalmologia');
INSERT INTO especialidade (id, nome) VALUES (5, 'Ortopedia');
INSERT INTO especialidade (id, nome) VALUES (6, 'Pediatria');

-- PESSOA (MEDICO)
INSERT INTO pessoa (id, sexo, dt_nascimento, nome, cpf, telefone, email, id_endereco) 
VALUES (2, 'M', '2019-05-22', 'Marcus Henrique Braga', null, '62984098007', 'drmarcus@gmail.com', null);

-- MEDICO
INSERT INTO medico (id, ativo, uf_crm, crm, id_pessoa, dt_criacao, dt_atualizacao) VALUES (1, 1, 'GO', '69201', 2, (SELECT NOW()), null);

-- ESPECIALIDADE MEDICA
INSERT INTO especialidade_medica (id_especialidade, id_medico) VALUES (1, 1);
INSERT INTO especialidade_medica (id_especialidade, id_medico) VALUES (2, 1);

-- ENDEREÇO (PACIENTE)
INSERT INTO endereco (id, bairro, logradouro, numero, complemento, id_cep) VALUES (2, 'Setor Central', 'Rua Paciente01', null, null, 1);

-- PESSOA (PACIENTE)
INSERT INTO pessoa (id, sexo, dt_nascimento, nome, cpf, telefone, email, id_endereco) 
VALUES (3, 'M', '2019-05-22', 'Paciente Teste01', '71777254078', '6235131288', 'paciente01@gmail.com', 2);
-- PACIENTE
INSERT INTO paciente (id, id_pessoa, dt_criacao, dt_atualizacao) VALUES (1, 3, (SELECT NOW()), null); 

-- PRONTUARIO 01 (PACIENTE)
INSERT INTO prontuario (id, numero, id_paciente, id_especialidade) 
VALUES (1, 1000, 1, 1);

-- PRONTUARIO 02 (PACIENTE)
INSERT INTO prontuario (id, numero, id_paciente, id_especialidade) 
VALUES (2, 1001, 1, 2);
