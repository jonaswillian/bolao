package bolao

import org.hibernate.criterion.RowCountProjection;
import org.hibernate.validator.cfg.defs.MaxDef;

import bolao.Palpite;
import restrito.Usuario
import bolao.Pessoa;

class JogoController {
	def springSecurityService

	def index() {
		def dataAtual = new Date().format("yyyy-MM-dd")
		def statusJogo
		def listaDatas = [] as Set
		def mapa = [:]

		listaDatas = Jogo.list().data.unique()


		listaDatas.each {data ->
			def jogos = Jogo.findAllByData(data)

			if(data.format("yyyy-MM-dd") <= dataAtual)
				statusJogo=1
			else
				statusJogo=0

			mapa[data] = jogos
		}
		
		Usuario usuario =Usuario.findByUsername(springSecurityService?.principal?.username)
		
		def lista = Palpite.createCriteria().list{
			createAlias("usuario","u")
			eq("u.id", usuario.id )
		}

		render(view:'index.gsp', model:[datasEJogos:mapa, jogoFinalizado:statusJogo, listaPalpites: lista])
	}

	def jogoAtual() {
		def dataAtual = new Date().format("yyyy-MM-dd")
		def jogosDeHoje = Jogo.createCriteria().list{ sqlRestriction"date_format(data, '%Y-%m-%d') = '${dataAtual}'" }

		def lista = Pessoa.list(sort: 'pontos', order: 'desc')

		render(view:'/index', model:[jogos:jogosDeHoje, jogadores:lista])
	}

	def salvarPalpite() {
		String msg=""
		Jogo jogo = Jogo?.get(params?.idJogo)
		Usuario usuario =Usuario.findByUsername(springSecurityService?.principal?.username)

		def lista = Palpite.createCriteria().list{
			createAlias("jogo","j")
			createAlias("usuario","u")
			eq("j.id", jogo.id )
			eq("u.id", usuario.id )
		}

		Palpite palpite
		if(lista.size() <= 0) {
			palpite = new Palpite()
		} else{
			lista.each {
				palpite = Palpite?.get(it?.id)
			}
		}

		palpite.dataPalpite	= new Date()
		int golsTime01 = params?.golsTime1
		int golsTime02 = params?.golsTime2

		palpite.golsTime1 = golsTime01-48
		palpite.golsTime2 = golsTime02-48

		palpite.jogo = jogo
		palpite.usuario = usuario

		if (!palpite.hasErrors() && palpite.save(flush:true,  failOnError: true)) {
			msg="Palpite cadastrado com sucesso!"
		}else {
			msg=""
		}
		render msg
	}
}
