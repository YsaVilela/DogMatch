package br.com.dogmatch.apiprincipal.DTO.Pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosPet(
		@NotBlank (message = "Nome é obrigatório") 
		String nome,
		
		@NotBlank (message = "Sobrenome é obrigatório") 
		String sobrenome,

		@NotBlank(message = "Data De Nascimento é obrigatório") 
		@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$", message = "Formato de data inválido. Utilize 00/00/0000") 
		String dataDeNascimento,
		
		@NotBlank(message = "Genêro é obrigatório") 
		String genero,
		
		@NotBlank(message = "Raca é obrigatório") 
		String raca,
		
		@NotBlank(message = "Cor é obrigatório")
		String cor,
		
		@NotBlank(message = "Porte é obrigatório")
		String porte,
		
//		@NotBlank(message = "Carteira de Vacinação é obrigatório")
//		MultipartFile carteiraDeVacinacao,
		
//		@NotBlank(message = "Foto de perfil é obrigatório")
//		MultipartFile fotoDePerfil,
		
		@NotBlank(message = "Descrição é obrigatória")
		String descricao,
		
		@NotBlank(message = "Interesse é obrigatório")
		String interesse,
		
		@NotNull(message = "Tutor é obrigatório")
		Long idTutor) {

}
