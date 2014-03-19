package bolao

import org.hibernate.criterion.RowCountProjection;
import org.hibernate.validator.cfg.defs.MaxDef;

import bolao.Palpite;
import restrito.Usuario
import bolao.Pessoa;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class AdminController {
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
		
		def lista = Jogo.list()

		render(view:'index.gsp', model:[datasEJogos:mapa, jogoFinalizado:statusJogo, listaResultados: lista])
	}
	
	//Inicio das incrementações
	
	def aFazer(){
		salvarResultado()
		atribuirPontuacao()
	}
	
	def sendMail(String t1, String g1, String t2, String g2){
		def pessoa = Pessoa.list()
		String tEmail = ""
		String tMensagem = (t1 + " " + g1 + " x " + g2 + " " + t2 + " Acesse bitbolao.com.br e confira sua pontuação!")
		String tNome = (t1 + " " + g1 + " x " + g2 + " " + t2)
		
		pessoa.each {
			tEmail = it.email
			EnviarEmail(tEmail, tMensagem, tNome)
		}
	}
	
	def EnviarEmail(String tEmail, String tMensagem, String tNome){
		
		Properties props = new Properties();
		/** ParÃ¢metros de conexÃ£o com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						 protected PasswordAuthentication getPasswordAuthentication()
						 {
							 //aqui precisa ser adicionado o usuario e senha
							 return new PasswordAuthentication("bitbolao@gmail.com", "quest@1806");
						 }
					});

		/** Ativa Debug para sessÃ£o */
		//session.setDebug(true)

		try {

			  Message message = new MimeMessage(session);
			  message.setFrom(new InternetAddress("bitbolao@gmail.com")); //Remetente

			  Address[] toUser = InternetAddress.parse(tEmail);  //DestinatÃ¡rio(s)

			  message.setRecipients(Message.RecipientType.TO, toUser);
			  message.setSubject("Bitbolão, Resultado do jogo: " + tNome);//Assunto
			  message.setText(tMensagem);
			  /**MÃ©todo para enviar a mensagem criada*/
			  Transport.send(message);
			  

		 } catch (MessagingException e) {
			  
		 }
	}
	
	def zeraPontuacao(){
		def lista1 = Pessoa.list()
		lista1.each {
			
			Pessoa pessoa1 = Pessoa?.get(it?.id)
			pessoa1.pontos = 0
			
			if (!pessoa1.hasErrors() && pessoa1.save(flush:true,  failOnError: true)) {
				def msg=""
			}
		}
	}
	
	def atribuirPontuacao(){
		def jogos = Jogo.list()
		def palpites = Palpite.list()
		zeraPontuacao()
		jogos.each {
			def cod = it.id
			def golsT1f = it.golsTime1
			def golsT2f = it.golsTime2
			def vencedor = 0
			if(golsT1f > golsT2f){
				vencedor = 1
			}
			if(golsT1f < golsT2f){
				vencedor = 2
			}
			palpites.each {
				if(it.jogo.id == cod){
					def golsT1p = it.golsTime1
					def golsT2p = it.golsTime2
					def pontos = 0
					def vencedorp = 0
					if(golsT1p > golsT2p){
						vencedorp = 1
					}
					if(golsT1p < golsT2p){
						vencedorp = 2
					}
					Usuario usuario = it.usuario
					def lista = Pessoa.list()
					lista.each {
						if(it.usuario == usuario){
							pontos = it.pontos
							if((golsT1f == golsT1p) && (golsT2f == golsT2p)){
								pontos = pontos + 3
							}
							if(golsT1f == golsT1p){
								pontos = pontos + (1/2)
							}
							if(golsT2f == golsT2p){
								pontos = pontos + (1/2)
							}
							if(vencedorp != 0){
								if(vencedorp == vencedor){
									pontos = pontos + 1
								}
							}
							
							Pessoa pessoa = Pessoa?.get(it?.id)
							pessoa.pontos = pontos
							
							if (!pessoa.hasErrors() && pessoa.save(flush:true,  failOnError: true)) {
								def msg=""
							}
						}
					}
					
				}
			}
		}
		
	}
	
	def salvarResultado(){
		String msg="Preencha corretamente o resultado!"
		Jogo jogo = Jogo?.get(params?.idJogo)
		
		String g1 = params?.golsTime1
		String g2 =params?.golsTime2
		
		if((g1 != "") && (g2 != "")){

			int golsTime01 = params?.golsTime1
			int golsTime02 = params?.golsTime2
		
		

			jogo.golsTime1 = golsTime01-48
			jogo.golsTime2 = golsTime02-48

			if (!jogo.hasErrors() && jogo.save(flush:true,  failOnError: true)) {
				msg="Resultado cadastrado com sucesso!"			
			}else {
				msg=""
			}
			render msg
		
			sendMail(jogo.time1.nome.toString(), jogo.golsTime1.toString(), jogo.time2.nome.toString(), jogo.golsTime2.toString())
			//sendMail(String.toString(jogo.time1), String.toString(jogo.golsTime1), String.toString(jogo.time2), String.toString(jogo.golsTime2))
		
		}else{
			render msg
		}
	}
	
	
	
	//Fim das incrementações

	def jogoAtual() {
		def dataAtual = new Date().format("yyyy-MM-dd")
		def jogosDeHoje = Jogo.createCriteria().list{ sqlRestriction"date_format(data, '%Y-%m-%d') = '${dataAtual}'" }

		def lista = Pessoa.list(sort: 'pontos', order: 'desc')

		render(view:'/index', model:[jogos:jogosDeHoje, jogadores:lista])
	}


}
