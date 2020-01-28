package com.gestorc.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.assembler.UsuarioInputDisassembler;
import com.gestorc.api.v1.assembler.UsuarioModelAssembler;
import com.gestorc.api.v1.model.input.SenhaInput;
import com.gestorc.api.v1.model.input.UsuarioComSenhaInput;
import com.gestorc.api.v1.model.input.UsuarioInput;
import com.gestorc.api.v1.model.usuario.UsuarioModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Usuario;
import com.gestorc.domain.repository.UsuarioRepository;
import com.gestorc.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public List<UsuarioModel> getAll() {
		List<Usuario> list = repository.findAll();
		list.forEach(l -> System.out.println(l));
		return usuarioModelAssembler.toCollectionModel(list);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("/{id}")
	public UsuarioModel getById(@PathVariable Long id) {
		Usuario usuario = service.isExists(id);
		return usuarioModelAssembler.toModel(usuario);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel save(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario objSave = usuarioInputDisassembler.toDomainObject(usuarioInput);
		objSave = service.salvar(objSave);
		return usuarioModelAssembler.toModel(objSave);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@PutMapping("/{id}")
	public UsuarioModel update(@PathVariable Long id, @RequestBody @Valid UsuarioInput obj) {
		Usuario objSave = service.isExists(id);
		usuarioInputDisassembler.copyToDomainObject(obj, objSave);
		objSave = service.salvar(objSave);
		return usuarioModelAssembler.toModel(objSave);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senha) {
		service.alterarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
	}

}
