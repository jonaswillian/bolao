package restrito

import bolao.Pessoa

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired	

	static constraints = {
		username blank: false
		password blank: false
	}

	static mapping = {
		password column: '`password`'
		table 'tb_usuario'
	}

	Set<Permissao> getAuthorities() {
		UsuarioPermissao.findAllByUsuario(this).collect { it.permissao } as Set
	}
	
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
