package bolao

import restrito.Usuario

class Palpite {
		Jogo jogo
		Usuario usuario
		int golsTime1
		int golsTime2
		Date dataPalpite
		
    static constraints = {
    }
	
	static mapping= {
		table 'tb_palpite'
		version false
		id generator: "native"
	}
	
	double pontuacao(){
		if ((jogo.golsTime1 ==this.golsTime1) && (jogo.golsTime2==this.golsTime2))
		{
			usuario.pontos += 4
			if (jogo.golsTime1!=jogo.golsTime2)
			{
				usuario.pontos += 3
			}
		}
		
		if ((jogo.golsTime1 == this.golsTime1) || (jogo.golsTime2 == this.golsTime2))
		{
			usuario.pontos += 0.5
		}
	}	
	
}
