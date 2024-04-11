package br.com.dogmatch.apiemail.service;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiemail.dto.DadosEmail;
import br.com.dogmatch.apiemail.dto.DadosArmazenar;


@Service
public class GerarEmailService{
	
	@Autowired
	private GerarCodigoService gerarCodigoService;
	
    @Autowired
    private GerenciarCodigoService gerenciarCodigoService;
	
	public String assuntoVerificação() {
		return "{DogMatch} Verificação de Email";
	}
	
	public String assuntoRedefinirSenha() {
		return "{DogMatch} Recuperação de Senha";
	}
	
	public String conteudoVerificacao(DadosEmail dados) {
		int codigo = gerarCodigoService.GerarCodigo();
		Year anoAtual = Year.now();
		String htmlBody = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"pt-br\">\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta\r\n"
				+ "      name=\"viewport\"\r\n"
				+ "      content=\"width\"\r\n"
				+ "      =\"device-width,\"\r\n"
				+ "      initial-scale=\"1.0\"\r\n"
				+ "    />\r\n"
				+ "    <title>DogMatch</title>\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />\r\n"
				+ "    <link\r\n"
				+ "      href=\"https://fonts.googleapis.com/css2?family=Amaranth:ital,wght@0,400;0,700;1,400;1,700&display=swap\"\r\n"
				+ "      rel=\"stylesheet\"\r\n"
				+ "    />\r\n"
				+ "    <style>\r\n"
				+ "      body {\r\n"
				+ "        font-family: \"Amaranth\", sans-serif;\r\n"
				+ "        font-size: 16px;\r\n"
				+ "        color: #333;\r\n"
				+ "        text-align: center;\r\n"
				+ "      }\r\n"
				+ "      p {\r\n"
				+ "        font-size: 18px;\r\n"
				+ "      }\r\n"
				+ "      .important-text {\r\n"
				+ "        font-weight: bold;\r\n"
				+ "      }\r\n"
				+ "      footer{\r\n"
				+ "        text-align: start;\r\n"
				+ "      }\r\n"
				+ "    </style>\r\n"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ "    <div></div>\r\n"
				+ "    <h1>Oii " + dados.nome() + ", seja bem vindo(a)!</h1>\r\n"
				+ "    <p>Utilize sua chave de acesso:</p>\r\n"
				+ "    <h2 style=\"font-size: 32px; color: #ff2366\"><strong>" + codigo + "</strong></h2>\r\n"
				+ "    <p>\r\n"
				+ "      Retorne ao aplicativo e insira a chave acima para confirmar sua\r\n"
				+ "      identidade.\r\n"
				+ "    </p>\r\n"
				+ "    <br />\r\n"
				+ "    <br />\r\n"
				+ "    <p>Obrigado!</p>\r\n"
				+ "    <p>Equipe DogMatch</p>\r\n"
				+ "  </body>\r\n"
				+ "  <footer>\r\n"
				+ "    <hr> \r\n"
				+ "    <br />\r\n"
				+ "    <p>Esse email foi enviado automaticamente e não recebe respostas</p>\r\n"
				+ "    <br />\r\n"
				+ "    <p>©DogMatch "+ anoAtual + "</p>\r\n"
				+ "  </footer>\r\n"
				+ "</html>\r\n";
		
		gerenciarCodigoService.armazenarCodigo(new DadosArmazenar(dados.email(),codigo));

		return htmlBody;
	}
	
	public String conteudoRedefinirSenha(DadosEmail dados) {
		int codigo = gerarCodigoService.GerarCodigo();
		Year anoAtual = Year.now();
		String htmlBody = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"pt-br\">\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta\r\n"
				+ "      name=\"viewport\"\r\n"
				+ "      content=\"width\"\r\n"
				+ "      =\"device-width,\"\r\n"
				+ "      initial-scale=\"1.0\"\r\n"
				+ "    />\r\n"
				+ "    <title>DogMatch</title>\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />\r\n"
				+ "    <link\r\n"
				+ "      href=\"https://fonts.googleapis.com/css2?family=Amaranth:ital,wght@0,400;0,700;1,400;1,700&display=swap\"\r\n"
				+ "      rel=\"stylesheet\"\r\n"
				+ "    />\r\n"
				+ "    <style>\r\n"
				+ "      body {\r\n"
				+ "        font-family: \"Amaranth\", sans-serif;\r\n"
				+ "        font-size: 16px;\r\n"
				+ "        color: #333;\r\n"
				+ "        text-align: center;\r\n"
				+ "      }\r\n"
				+ "      p {\r\n"
				+ "        font-size: 18px;\r\n"
				+ "      }\r\n"
				+ "      .important-text {\r\n"
				+ "        font-weight: bold;\r\n"
				+ "      }\r\n"
				+ "      footer{\r\n"
				+ "        text-align: start;\r\n"
				+ "      }\r\n"
				+ "    </style>\r\n"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ "    <div></div>\r\n"
				+ "    <h1>Oii " + dados.nome() + ", esqueceu sua senha?</h1>\r\n"
				+ "    <p>Utilize sua chave de acesso:</p>\r\n"
				+ "    <h2 style=\"font-size: 32px; color: #ff2366\"><strong>" + codigo + "</strong></h2>\r\n"
				+ "    <p>\r\n"
				+ "      Retorne ao aplicativo e insira a chave acima para conseguir redefinir sua senha.\r\n"
				+ "    </p>\r\n"
				+ "    <br />\r\n"
				+ "    <br />\r\n"
				+ "    <p>Obrigado!</p>\r\n"
				+ "    <p>Equipe DogMatch</p>\r\n"
				+ "  </body>\r\n"
				+ "  <footer>\r\n"
				+ "    <hr> \r\n"
				+ "    <br />\r\n"
				+ "    <p>Esse email foi enviado automaticamente e não recebe respostas</p>\r\n"
				+ "    <br />\r\n"
				+ "    <p>©DogMatch "+ anoAtual + "</p>\r\n"
				+ "  </footer>\r\n"
				+ "</html>\r\n";
		
		gerenciarCodigoService.armazenarCodigo(new DadosArmazenar(dados.email(),codigo));

		return htmlBody;
	}
}
