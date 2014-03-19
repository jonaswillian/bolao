package bolao

import bolao.Pessoa;
import restrito.Permissao;
import restrito.Usuario;
import restrito.UsuarioPermissao;

class CadastroController {

	def index() {
		if (params.aceitar==null) {
			//render(controller:'regras', view:'index.gsp')
			redirect(controller: 'regras', view:'index.gsp')
		}
	}

	def salvar(){
		String msg=""
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

		if(!LoginJaExistente() && !verificarSenhaComConfirmacaoSenha()){

			if (!pessoa.hasErrors() && usuario.save(flush:true,  failOnError: true) && pessoa.save(flush:true,  failOnError: true) ) {

				msg="Usuário cadastrado com sucesso!"
			}
			else {
				msg="Usuário não cadastrado! Problemas no servidor"
			}


			if (!usuario.authorities.contains(permissaoUsuario)) {
				UsuarioPermissao.create usuario, permissaoUsuario
			}
		}
		else{
			msg="Erro no cadastro: Login ou Email já existe."
		}
		
		render msg
	}


	boolean LoginJaExistente(){

		def checkLogin = Usuario.findAllByUsername(params.login)
		def checkEmail = Pessoa.findAllByEmail(params.email)
		
		if((checkLogin.size() > 0) && (params.username != checkLogin) || (checkEmail.size() > 0) && (params.email != checkEmail)){
			return true
		}else{
			return false
		}
	}

	boolean verificarSenhaComConfirmacaoSenha(){
		if(params.senha!=params.senhaConfirmada) {
			return true
		}
		return false
	}
	
	
}
