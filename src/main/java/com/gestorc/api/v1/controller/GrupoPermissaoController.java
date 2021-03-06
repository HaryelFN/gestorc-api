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

import com.gestorc.api.v1.assembler.PermissaoModelAssembler;
import com.gestorc.api.v1.model.permissao.PermissaoModel;
import com.gestorc.core.security.CheckSecurity;
import com.gestorc.domain.model.Grupo;
import com.gestorc.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController {

	@Autowired
	private GrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar

	@GetMapping
	public List<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

//		CollectionModel<PermissaoModel> permissoesModel 
//			= permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
//				.removeLinks();
//		
//		permissoesModel.add(algaLinks.linkToGrupoPermissoes(grupoId));
//		
//		if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
//			permissoesModel.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
//		
//			permissoesModel.getContent().forEach(permissaoModel -> {
//				permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
//						grupoId, permissaoModel.getId(), "desassociar"));
//			});
//		}

		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

}
