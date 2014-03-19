package bolao

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class FaleConoscoController {
	String msg=""

    def index() { }
	
	def enviar() {
		
		String nome  = params.txtNome
	    String email = params.txtEmail
		String mensagem = params.txtMensagem
		
	
		EnviarEmail(email, mensagem, nome)
		msg = " e-mail: "+ email
		
		render msg
	}
	void EnviarEmail(String tEmail, String tMensagem, String tNome){
		
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
			  message.setFrom(new InternetAddress(tEmail)); //Remetente

			  Address[] toUser = InternetAddress.parse("bitbolao@gmail.com");  //Destinatário(s)

			  message.setRecipients(Message.RecipientType.TO, toUser);
			  message.setSubject("Fale Conosco: " + tNome);//Assunto
			  message.setText(tMensagem);
			  /**Método para enviar a mensagem criada*/
			  Transport.send(message);
			  msg = "Mensagem enviada com sucesso!"

		 } catch (MessagingException e) {
			  msg= "Erro no envio do email. Tente mais tarde!"
	  }
  }
}
