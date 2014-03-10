package bolao

class Time {
		String nome
		String bandeira
		
		static mapping= {
		table 'tb_time'
		version false
		id generator: "native"
		}
		
    static constraints = {
    }
}
