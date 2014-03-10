class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"jogo", action:"jogoAtual")	
		//"/"(controller:"ranking", action:"atualizar")
		"500"(view:'/error')
	}
}
