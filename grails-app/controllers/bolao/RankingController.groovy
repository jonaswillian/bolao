package bolao
import bolao.Pessoa

class RankingController {

    def index() { }
	
	def atualizar() {
		//def lista = Pessoa.list(sort: 'pontos', order: 'desc')		
		def lista = Pessoa.list()
		render(view:'/index', model:[jogadores:lista])
	}
}
