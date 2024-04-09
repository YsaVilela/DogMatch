package br.com.dogmatch.apiemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GerarEmailVerificacao {
	
	@Autowired
	private GerarCodigoService gerarCodigoService;
	
	public String assunto() {
		return "{DogMatch} Verificação de Email";
	}
	
	public String conteudo(String nome) {
		int codigo = gerarCodigoService.GerarCodigo();
		String htmlBody = "<h1>Olá, " + nome + ", seja bem vindo!</h1>"
                + "<p></p>"
                + "<p>Segue um exemplo de lista:</p>"
                + "<ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>";
		
		return htmlBody;
	}

}
