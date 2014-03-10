package restrito

class Permissao {

	String authority

	static mapping = {
		cache true
		table 'tb_permissao'
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
