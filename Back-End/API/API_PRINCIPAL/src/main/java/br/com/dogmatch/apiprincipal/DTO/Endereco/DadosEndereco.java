package br.com.dogmatch.apiprincipal.DTO.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		@NotBlank(message = "CEP é obrigatório") 
		@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido") 
		String cep,

		@NotBlank(message = "Logradouro  é obrigatório") 
		String logradouro,

		@NotNull(message = "Número  é obrigatório") 
		Long numero,

		String complemento,

		@NotBlank(message = "Bairro  é obrigatório") 
		String bairro,

		@NotBlank(message = "Cidade  é obrigatório") 
		String cidade,

		@NotBlank(message = "UF  é obrigatório") 
		String uf) {
}
