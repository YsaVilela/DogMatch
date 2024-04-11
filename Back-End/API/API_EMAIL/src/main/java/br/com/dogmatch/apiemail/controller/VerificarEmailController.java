package br.com.dogmatch.apiemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiemail.dto.ConteudoEmail;
import br.com.dogmatch.apiemail.dto.DadosEmail;
import br.com.dogmatch.apiemail.dto.DadosValidacao;
import br.com.dogmatch.apiemail.dto.MensagemDeSucesso;
import br.com.dogmatch.apiemail.service.EnviarEmailService;
import br.com.dogmatch.apiemail.service.GerarEmailService;
import br.com.dogmatch.apiemail.service.GerenciarCodigoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("enviarEmail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VerificarEmailController {
	
	@Autowired
	private EnviarEmailService enviarEmailService;
	
	@Autowired
	private GerarEmailService gerarEmailService;
	
	@Autowired
	private GerenciarCodigoService gerenciarCodigoService;

	@PostMapping ("/verificarEmail")
    public ResponseEntity<MensagemDeSucesso> verificarEmail(@RequestBody @Valid DadosEmail dados) {
		String to = dados.email();
		String subject = gerarEmailService.assuntoVerificação();
		String body = gerarEmailService.conteudoVerificacao(dados);
		ConteudoEmail conteudoEmail = new ConteudoEmail(to, subject, body);
		
		return ResponseEntity.status(HttpStatus.OK).body(enviarEmailService.enviarEmail(conteudoEmail));
    }
	
	@PostMapping ("/redefinirSenha")
    public ResponseEntity<MensagemDeSucesso> redefinirSenha (@RequestBody @Valid DadosEmail dados) {
		String to = dados.email();
		String subject = gerarEmailService.assuntoRedefinirSenha();
		String body = gerarEmailService.conteudoRedefinirSenha(dados);
		ConteudoEmail conteudoEmail = new ConteudoEmail(to, subject, body);
		
		return ResponseEntity.status(HttpStatus.OK).body(enviarEmailService.enviarEmail(conteudoEmail));
    }
	
	@PostMapping ("/validarCodigo")
    public ResponseEntity<MensagemDeSucesso> validarCodigo (@RequestBody @Valid DadosValidacao dados) {
		return ResponseEntity.status(HttpStatus.OK).body(gerenciarCodigoService.verificarCodigo(dados));
    }
	
}
