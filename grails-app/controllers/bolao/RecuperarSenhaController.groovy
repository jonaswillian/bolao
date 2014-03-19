package bolao

import bolao.Pessoa
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import restrito.Usuario

class RecuperarSenhaController {

	def springSecurityService
	String msg=""
	
	def index() { }
	
	def enviar() {		
		Pessoa pessoa = new Pessoa()
		String email = params?.email
		String novaSenha
		pessoa = Pessoa.findByEmail(email)
		
		if(pessoa != null)
		{			
			int id = pessoa.id			
			Usuario usuario = Usuario.get(pessoa.usuario.id)
									
			Random ram = new Random()
			novaSenha = (ram.nextInt(100000) + 10).toString()+"bboa"
						
			EnviarEmail(email, novaSenha)
			usuario.password = novaSenha
			
			if(!usuario.hasErrors() && usuario.save(flush:true,  failOnError: true)){
				//println "Nova senha enviada no e-mail: "+ email
				msg = "Nova senha enviada no e-mail: "+ email
			}
		}else
		{
			//println "Erro: Email de usuário não encontrado!"
			msg="Erro: Email não encontrado!"
		}
		
		render msg
	}

	
	void EnviarEmail(String txtemail, String novaSenha){
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
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

		/** Ativa Debug para sessão */
		//session.setDebug(true)

		try {

			  Message message = new MimeMessage(session);
			  message.setFrom(new InternetAddress("bitbolao@gmail.com")); //Remetente

			  Address[] toUser = InternetAddress.parse(txtemail);  //Destinatário(s)

			  message.setRecipients(Message.RecipientType.TO, toUser);
			  message.setSubject("BitBolão: Recuperação de Senha");//Assunto
			  message.setText("Você solicitou a alteração de sua senha! A seguinte senha foi criada automaticamente: " + novaSenha + " acesse o site e faça login com a nova senha!");
			  /**Método para enviar a mensagem criada*/
			  Transport.send(message);

		 } catch (MessagingException e) {
			  msg= "Erro no envio do email. Tente mais tarde!"
	  }
  }
	
}
