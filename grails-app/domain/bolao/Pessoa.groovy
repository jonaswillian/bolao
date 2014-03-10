package bolao

import java.util.Date;

import restrito.Usuario;

class Pessoa {
	String nome
	String email
	double pontos
	Date dataCadastro
	Date ultimoAcesso

	Usuario usuario
	
	static belongsTo = [usuario:Usuario]
	
	static constraints = {
		nome blank: false
		email unique:true, email:true
		usuario nullable:true
	}
	
	static mapping = {
		table 'tb_pessoa'
		version false
		id generator: "native"
	}
}
