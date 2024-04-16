package br.com.dogmatch.apiemail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEmail(
		@NotBlank(message = "Email é obrigatório") 
		@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido") 
		String email, 
		
		@NotBlank (message = "Nome é obrigatório") 
		String nome) {
}
