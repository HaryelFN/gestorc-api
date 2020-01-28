package com.gestorc.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestorc.domain.exception.UsuarioNotFoundException;
import com.gestorc.domain.exception.BusinessException;
import com.gestorc.domain.model.Grupo;
import com.gestorc.domain.model.Usuario;
import com.gestorc.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoService cadastroGrupo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Usuario salvar(Usuario obj) {
		usuarioRepository.detach(obj);

		Optional<Usuario> objSave = usuarioRepository.findByLogin(obj.getLogin());

		if (objSave.isPresent() && !objSave.get().equals(obj)) {
			throw new BusinessException(String.format("Já existe um usuário cadastrado com o login %s", obj.getLogin()));
		}

		if (obj.isNovo()) {
			obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		}

		return usuarioRepository.save(obj);
	}

	@Transactional
	public void alterarSenha(Long id, String senhaAtual, String novaSenha) {
		Usuario objSave = isExists(id);

		if (!passwordEncoder.matches(senhaAtual, objSave.getSenha())) {
			throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
		}

		objSave.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = isExists(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.removerGrupo(grupo);
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = isExists(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.adicionarGrupo(grupo);
	}

	public Usuario isExists(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
	}

}
