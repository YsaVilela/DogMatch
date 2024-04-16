package br.com.dogmatch.apiprincipal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Mensagem;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosPet;
import br.com.dogmatch.apiprincipal.Service.PetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pet")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping("cadastrar")
	public ResponseEntity<Mensagem> cadastrar(@RequestBody @Valid DadosPet dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem(petService.cadastrar(dados)));
	}
	
	
}
