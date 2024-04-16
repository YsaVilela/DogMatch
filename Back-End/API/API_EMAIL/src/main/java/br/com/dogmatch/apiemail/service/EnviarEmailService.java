package br.com.dogmatch.apiemail.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import br.com.dogmatch.apiemail.dto.ConteudoEmail;
import br.com.dogmatch.apiemail.dto.MensagemDeSucesso;
import br.com.dogmatch.apiemail.infra.exception.InvalidEmailException;

@Service
public class EnviarEmailService {
	
	public MensagemDeSucesso enviarEmail(ConteudoEmail email) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			String username = "DogMatch.suporte@gmail.com";
			String password = "lypt yxhi nqjv howd";

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.to()));
			message.setSubject(email.subject());
			message.setContent(email.body(), "text/html");

			Transport.send(message);
			
			return new MensagemDeSucesso("Email enviado com sucesso", email.to());
		} catch (MessagingException e) {
			throw new InvalidEmailException("Não foi possivel enviar o email.");
		}
	}

}
