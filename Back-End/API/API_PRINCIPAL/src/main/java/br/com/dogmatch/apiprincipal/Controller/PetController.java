package br.com.dogmatch.apiprincipal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Mensagem;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosDetalhamentoFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosDetalhamentoPedigree;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosPedigree;
import br.com.dogmatch.apiprincipal.Service.PetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pet")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping("cadastrar")
	public ResponseEntity<Mensagem> cadastrarPet(@RequestBody @Valid DadosPet dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem(petService.cadastrar(dados)));
	}
	
	@PostMapping("cadastrar/pedigree")
	public ResponseEntity<Mensagem> cadastrarPedigree(@RequestBody @Valid DadosPedigree dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem(petService.cadastrarPedigree(dados)));
	}
	
	@PostMapping("cadastrar/foto")
	public ResponseEntity<Mensagem> cadastrarFoto(@RequestBody @Valid DadosFoto dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem(petService.cadastrarFoto(dados)));
	}
	
	@GetMapping("buscar/pedigree/{idPet}")
	public ResponseEntity<DadosDetalhamentoPedigree> buscarPedigree(@PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(petService.buscarPedigree(idPet));
	}
	
	@GetMapping("buscar/fotos/{idPet}")
	public ResponseEntity<List<DadosDetalhamentoFoto>> buscarFotos(@PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(petService.buscarFotos(idPet));
	}
	
	@PutMapping("atualizar/pet/{idPet}")
	public ResponseEntity<Mensagem> atualizarPet(@RequestBody @Valid DadosPet dados, @PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.atualizarPet(idPet, dados)));
	}
	
	@PutMapping("atualizar/pedigree")
	public ResponseEntity<Mensagem> atualizarPedigree(@RequestBody @Valid DadosPedigree dados) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.atualizarPedigree(dados)));
	}
	
	@DeleteMapping("deletar/pedigree/{idPet}")
	public ResponseEntity<Mensagem> deletarPedigree(@PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.deletarPedigree(idPet)));
	}
	
	@DeleteMapping("deletar/foto/{idFoto}")
	public ResponseEntity<Mensagem> deletarFoto(@PathVariable Long idFoto) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.deletarFoto(idFoto)));
	}
	
	@PutMapping("desativar/{idPet}")
	public ResponseEntity<Mensagem> desativarPet(@PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.desativarPet(idPet)));
	}
	
	@PutMapping("ativar/{idPet}")
	public ResponseEntity<Mensagem> ativarPet(@PathVariable Long idPet) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(petService.ativarPet(idPet)));
	}
}
