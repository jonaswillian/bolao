package bolao

class Jogo {
		Time time1
		Time time2
		Date data
		int golsTime1
		int golsTime2
		
	static hasMany=[palpites:Palpite]		
				
    static constraints = {
    }
	
	static mapping= {
		table 'tb_jogo'
		version false
		id generator: "native"
	}
}
