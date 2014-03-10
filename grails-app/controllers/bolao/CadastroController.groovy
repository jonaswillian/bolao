package bolao

import bolao.Pessoa;
import restrito.Permissao;
import restrito.Usuario;
import restrito.UsuarioPermissao;

class CadastroController {

	def index() {
		if (params.aceitar=="") {
			render(controller:'regras', view:'index.gsp')
		}
	}

	def salvar(){

		String nome  = params.nome
		String email = params.email
		String login = params.login
		String senha = params.senha
		String senhaConfirmada = params.senhaConfirmada

		def pessoa = new Pessoa()
		pessoa.nome = nome	
		pessoa.email = email
		pessoa.dataCadastro = new Date()
		pessoa.ultimoAcesso = new Date()
		pessoa.pontos = 0;

		def permissaoUsuario = Permissao.findByAuthority('ROLE_USER') ?: new Permissao(authority: 'ROLE_USER').save(failOnError: true)
		
		def usuario = Usuario.findByUsername(login) ?: new Usuario(
			username: login,
			password: senha,
			enabled: true)

		pessoa.usuario = usuario
		
		if (!pessoa.hasErrors() && usuario.save(flush:true,  failOnError: true) && pessoa.save(flush:true,  failOnError: true) && senha == senhaConfirmada) {
			flash.message="Usuário cadastrado com sucesso!"			
		}else {
			flash.error="Usuário não cadastrado!"			
		}
		
		redirect(action:'index')
		
		if (!usuario.authorities.contains(permissaoUsuario)) {
			UsuarioPermissao.create usuario, permissaoUsuario
		}

		render(view:'/cadastro/index')
	}
}
