package br.com.dogmatch.apiprincipal.DTO.Pet.Foto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosFoto(
//		@NotBlank(message = "Foto é obrigatório")
//		MultipartFile foto,
		
		@NotNull(message = "Data De publicação é obrigatório") 
		LocalDateTime dataPublicacao,
		
		String legenda,
		
		@NotNull(message = "Pet é obrigatório")
		Long idPet
		) {

}
 