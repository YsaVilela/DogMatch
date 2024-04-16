package br.com.dogmatch.apiemail.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosValidacao(
		@NotBlank(message = "Email é obrigatório") 
		@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido") 
		String email,
		
		@NotNull(message = "Codigo é obrigatório") 
		int codigo,
		
		@NotNull(message = "Data de expiracao é obrigatório") 
		LocalDateTime dataDeEnvio) {

}
