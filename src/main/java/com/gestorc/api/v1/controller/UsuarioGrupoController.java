package com.gestorc.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestorc.api.v1.assembler.GrupoModelAssembler;
import com.gestorc.api.v1.model.grupo.GrupoModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Usuario;
import com.gestorc.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public List<GrupoModel> listar(@PathVariable Long idUsuario) {
		Usuario objSave = service.isExists(idUsuario);
		return grupoModelAssembler.toCollectionModel(objSave.getGrupos());
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		service.desassociarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		service.associarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

}