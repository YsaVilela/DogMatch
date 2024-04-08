package br.com.dogmatch.apiprincipal.DTO.Tutor;

import br.com.dogmatch.apiprincipal.DTO.Endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosTutor(
		@NotBlank (message = "Nome é obrigatório") 
		String nome,
		
		@NotBlank (message = "Sobrenome é obrigatório") 
		String sobrenome,

		@NotBlank(message = "Data De Nascimento é obrigatório") 
		@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$", message = "Formato de data inválido. Utilize 00/00/0000") 
		String dataDeNascimento,
		
		@NotBlank(message = "Genêro é obrigatório") 
		String genero,
		
		@NotBlank(message = "CPF é obrigatório") 
		@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Cpf inválido")
		String cpf,

		@NotBlank(message = "Email é obrigatório") 
		@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email inválido") 
		String email,

		@NotBlank(message = "Telefone é obrigatório") 
		@Pattern(regexp = "^\\(\\d{2}\\)\\d{5}-\\d{4}$", message = "Telefone inválido") 
		String telefone,
		
		@NotBlank(message = "Senha é obrigatório") 
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Senha inválida, deve conter pelo menos 8 digitos, números e carcteres.") 
		String senha,

		@Valid  
		DadosEndereco endereco) {
}
