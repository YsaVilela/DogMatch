package br.com.dogmatch.apiprincipal.DTO.Pet;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosFoto(
//		@NotBlank(message = "Foto é obrigatório")
//		MultipartFile foto,
		
		@NotBlank(message = "Data De publicação é obrigatório") 
		LocalDateTime dataPublicacao,
		
		String legenda,
		
		@NotNull(message = "Pet é obrigatório")
		Long idPet
		) {

}
