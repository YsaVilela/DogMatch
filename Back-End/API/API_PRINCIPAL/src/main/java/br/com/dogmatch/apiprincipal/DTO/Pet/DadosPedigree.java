package br.com.dogmatch.apiprincipal.DTO.Pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosPedigree(
		
		@NotBlank(message = "Número do RG é obrigatório") 
		@Pattern(regexp = "^[A-Z]{3}\\/\\d{2}\\/\\d{5}$", message = "Formato do número de registro inválido") 
		String rg,
		
		@NotBlank(message = "Data De Nascimento é obrigatório") 
		@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$", message = "Formato de data inválido. Utilize 00/00/0000") 
		String dataDeEmissao,
		
//		@NotBlank(message = "Foto do documento é obrigatório")
//		MultipartFile fotoPedigree,
		
		@NotNull(message = "Pet é obrigatório")
		Long idPet
		) {

}
