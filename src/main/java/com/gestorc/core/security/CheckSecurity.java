package com.gestorc.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Especialidades {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ROLE_FIND_ESPECIALIDADE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface find { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_ADD_ESPECIALIDADE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface add { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_REMOVE_ESPECIALIDADE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface remove { }
	}
	
	public @interface Medicos {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ROLE_FIND_MEDICO') and " + "@mySecurity.isAutenticado()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface find { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_ADD_MEDICO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface add { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_REMOVE_MEDICO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface remove { }
	}
	
	public @interface Pacientes {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ROLE_FIND_PACIENTE') and " + "@mySecurity.isAutenticado()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface find { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_ADD_PACIENTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface add { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ROLE_REMOVE_PACIENTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface remove { }
	}
	
//	public @interface Restaurantes {
//		
//		@PreAuthorize("@algaSecurity.podeGerenciarCadastroRestaurantes()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeGerenciarCadastro { }
//
//		@PreAuthorize("@algaSecurity.podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeGerenciarFuncionamento { }
//		
//		@PreAuthorize("@algaSecurity.podeConsultarRestaurantes()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeConsultar { }
//		
//	}
//	
//	public @interface Pedidos {
//		
//		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
//		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
//				+ "@algaSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or "
//				+ "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeBuscar { }
//		
//		@PreAuthorize("@algaSecurity.podePesquisarPedidos(#filtro.clienteId, #filtro.restauranteId)")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodePesquisar { }
//		
//		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeCriar { }
//		
//		@PreAuthorize("@algaSecurity.podeGerenciarPedidos(#codigoPedido)")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeGerenciarPedidos { }
//		
//	}
//	
//	public @interface FormasPagamento {
//		
//		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeEditar { }
//
//		@PreAuthorize("@algaSecurity.podeConsultarFormasPagamento()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeConsultar { }
//		
//	}
//	
//	public @interface Cidades {
//		
//		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeEditar { }
//
//		@PreAuthorize("@algaSecurity.podeConsultarCidades()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeConsultar { }
//		
//	}
//	
//	public @interface Estados {
//		
//		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeEditar { }
//
//		@PreAuthorize("@algaSecurity.podeConsultarEstados()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeConsultar { }
//		
//	}
	
	public @interface UsuariosGruposPermissoes {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and " + "@mySecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@mySecurity.usuarioAutenticadoIgual(#usuarioId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario { }

		@PreAuthorize("@mySecurity.podeEditarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		

		@PreAuthorize("@mySecurity.podeConsultarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}
	
//	public @interface Estatisticas {
//		
//		@PreAuthorize("@algaSecurity.podeConsultarEstatisticas()")
//		@Retention(RUNTIME)
//		@Target(METHOD)
//		public @interface PodeConsultar { }
//		
//	}
	
}
