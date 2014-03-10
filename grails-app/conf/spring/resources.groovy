// Place your Spring DSL code here
import bolao.AutenticarProvider;

beans = {
	autenticacao(AutenticarProvider) {
		springSecurityService = ref("springSecurityService")
	}
}
