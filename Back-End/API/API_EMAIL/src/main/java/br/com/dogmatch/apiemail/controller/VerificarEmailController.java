package br.com.dogmatch.apiemail.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiemail.entity.EmailVerificacao;
import br.com.dogmatch.apiemail.service.GerarCodigoService;
import br.com.dogmatch.apiemail.service.GerarEmailVerificacao;

@RestController
@RequestMapping ("verificarEmail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VerificarEmailController {
	
	@Autowired
	private GerarEmailVerificacao gerarEmailVerificacaoService;

	@PostMapping()
    public String sendEmail(@RequestBody String email, String nome) {
        String to = email;
        String subject = gerarEmailVerificacaoService.assunto();
        String body = gerarEmailVerificacaoService.conteudo(nome);

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); 
            props.put("mail.smtp.port", "587"); 
            props.put("mail.smtp.auth", "true"); 
            props.put("mail.smtp.starttls.enable", "true"); 

            String username = "ysadora.vi.l.silva@gmail.com";
            String password = "pcha gvnb nhrq gmtn";

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); 
            message.setSubject(subject); 
            message.setText(body); 

            Transport.send(message);
            return "Email enviado com sucesso para " + to;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Erro ao enviar email: " + e.getMessage();
        }
    }
}
