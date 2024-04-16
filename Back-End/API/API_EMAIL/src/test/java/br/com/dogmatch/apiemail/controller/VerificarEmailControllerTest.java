package br.com.dogmatch.apiemail.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.dogmatch.apiemail.dto.DadosEmail;
import br.com.dogmatch.apiemail.dto.DadosValidacao;
import br.com.dogmatch.apiemail.dto.MensagemDeSucesso;import br.com.dogmatch.apiemail.repository.VerificacaoRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class VerificarEmailControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private VerificacaoRepository verificacaoRepository;

    @LocalServerPort
    private int port;
    
	@Test
	@DisplayName("envia email de boa vindas com codigo para verificação")
	void verificarEmail() {	
		DadosEmail dados = new DadosEmail("teste@hotmail.com", "User");

        ResponseEntity<MensagemDeSucesso> response = restTemplate.postForEntity("/enviarEmail/verificarEmail", dados, MensagemDeSucesso.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	@DisplayName("envia email de recuperação de senha com codigo para verificação")
	void redefinirSenha() {	
		DadosEmail dados = new DadosEmail("teste@hotmail.com", "User");

        ResponseEntity<MensagemDeSucesso> response = restTemplate.postForEntity("/enviarEmail/redefinirSenha", dados, MensagemDeSucesso.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	@DisplayName("recebe e valida o codigo")
	void validarCodigo() {	
		DadosEmail dadosEnviar = new DadosEmail("teste@hotmail.com", "User");
		restTemplate.postForEntity("/enviarEmail/verificarEmail", dadosEnviar, MensagemDeSucesso.class);
		int codigoValido = verificacaoRepository.findByEmail("teste@hotmail.com").get().getCodigo();

		DadosValidacao dados = new DadosValidacao("teste@hotmail.com", codigoValido, LocalDateTime.now());

        ResponseEntity<MensagemDeSucesso> response = restTemplate.postForEntity("/enviarEmail/validarCodigo", dados, MensagemDeSucesso.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());		
	}
	
	@Test
	@DisplayName("recebe codigo inválido e retorna erro 400")
	void codigoInvalido() {	
		DadosValidacao dados = new DadosValidacao("teste@hotmail.com", 12345, LocalDateTime.now());

        ResponseEntity<JsonNode> response = restTemplate.postForEntity("/enviarEmail/validarCodigo", dados, JsonNode.class);

		assertTrue(response.getStatusCode().is4xxClientError());		
		
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("mensagem").asText()).isEqualTo("Código Inválido");
	}
	
	@Test
	@DisplayName("recebe codigo expirado e retorna erro 400")
	void codigoExpirado() {	
		DadosEmail dadosEnviar = new DadosEmail("teste@hotmail.com", "User");
		restTemplate.postForEntity("/enviarEmail/verificarEmail", dadosEnviar, MensagemDeSucesso.class);
		int codigoValido = verificacaoRepository.findByEmail("teste@hotmail.com").get().getCodigo();

		DadosValidacao dados = new DadosValidacao("teste@hotmail.com", codigoValido, LocalDateTime.now().plusMinutes(20));

        ResponseEntity<JsonNode> response = restTemplate.postForEntity("/enviarEmail/validarCodigo", dados, JsonNode.class);

		assertTrue(response.getStatusCode().is4xxClientError());
		
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("mensagem").asText()).isEqualTo("Código Expirado");
	}
}
