import bolao.Pessoa;
import restrito.Permissao;
import restrito.Usuario;
import restrito.UsuarioPermissao;

class BootStrap {

	def init = { servletContext ->
		
		def permissaoUsuario = Permissao.findByAuthority('ROLE_USER') ?: new Permissao(authority: 'ROLE_USER').save(failOnError: true)
		def permissaoAdmin = Permissao.findByAuthority('ROLE_ADMIN') ?: new Permissao(authority: 'ROLE_ADMIN').save(failOnError: true)
		//def endereco = new Endereco(id:1, logradouro:"Rua walter xxx", bairro:"vila  rica", cidade:"Lorena", estado:"SP", numero: new Long(1823))
		def pessoa = new Pessoa(id: 1, nome: 'Jão', email: 'admin@gmail.com.br', telefone: '(12) 3100-2541', tipo: '0')
		
		//empresa.endereco = endereco
		
		def usuarioAdmin = Usuario.findByUsername('admin') ?: new Usuario(
			username: 'admin',
			password: 'admin',
			enabled: true,
			pessoa: pessoa).save(flush:true, failOnError: true)
		
		if (!usuarioAdmin.authorities.contains(permissaoAdmin)) {
			UsuarioPermissao.create usuarioAdmin, permissaoAdmin
		}
		if (!usuarioAdmin.authorities.contains(permissaoUsuario)) {
			UsuarioPermissao.create usuarioAdmin, permissaoUsuario
		}
		
	}
	def destroy = {
	}
}
