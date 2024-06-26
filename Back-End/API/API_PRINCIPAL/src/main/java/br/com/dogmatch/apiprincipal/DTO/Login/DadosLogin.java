package br.com.dogmatch.apiprincipal.DTO.Login;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
		@NotBlank(message = "Login é obrigatório") 
		String login,
		
		@NotBlank(message = "Senha é obrigatório") 
		String senha) {
}
