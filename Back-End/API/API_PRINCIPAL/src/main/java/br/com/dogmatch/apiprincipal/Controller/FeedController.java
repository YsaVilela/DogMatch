package br.com.dogmatch.apiprincipal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.DetalhamentoSolicitacao;
import br.com.dogmatch.apiprincipal.DTO.FiltroFeed;
import br.com.dogmatch.apiprincipal.DTO.Mensagem;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.Service.FeedService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("feed")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FeedController {

	@Autowired
	public FeedService feedService;

	@GetMapping()
	public ResponseEntity<List<DadosFeedPet>> buscar(HttpServletRequest request, 
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = false)String localizacaoAtual, 
			@RequestParam (required = false)Long distanciaMaxima, 
			@RequestParam (required = true)Long idadeMinima,
			@RequestParam (required = true)Long idadeMaxima, 
			@RequestParam (required = false)String raca, 
			@RequestParam (required = true)boolean possuiPedigree,
			@RequestParam (required = false)String porte,
			@RequestParam (required = true)boolean castrado) {
		Long tutorId = (Long) request.getAttribute("idUsuario");
		FiltroFeed filtros = new FiltroFeed(tutorId, idPet, localizacaoAtual,
				distanciaMaxima, idadeMinima, idadeMaxima, raca, possuiPedigree, porte, castrado);

		return ResponseEntity.status(HttpStatus.OK).body(feedService.buscarPetFeed(filtros));
	}
	
	@PostMapping("recusa")
	public ResponseEntity<Mensagem> recusar(HttpServletRequest request,
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = true) Long idPetRecusado) {
		Long tutorId = (Long) request.getAttribute("idUsuario");

		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(feedService.recusarPet(tutorId, idPet, idPetRecusado)));
	}
	
	@PostMapping("solicitacao")
	public ResponseEntity<Mensagem> solicitar(HttpServletRequest request,
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = true) Long idPetSolicitado) {
		Long tutorId = (Long) request.getAttribute("idUsuario");
		
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(feedService.solicitarPet(tutorId, idPet, idPetSolicitado)));
	}
	
	@PostMapping("aceitarSolicitacao")
	public ResponseEntity<Mensagem> aceitarSolicitacao(
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = true) Long idPetSolicitante) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(feedService.aceitarSolicitacaoPet(idPet, idPetSolicitante)));
	}
	
	@PostMapping("recusarSolicitacao")
	public ResponseEntity<Mensagem> recusarSolicitacao(
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = true) Long idPetSolicitante) {
		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(feedService.recusarSolicitacaoPet(idPet, idPetSolicitante)));
	}
	
	@GetMapping("buscarSolicitacoes")
	public ResponseEntity<List<DetalhamentoSolicitacao>> buscarSolicitacoes(HttpServletRequest request, 
			@RequestParam (required = true) Long idPet,
			@RequestParam (required = false)String localizacaoAtual) {
		Long tutorId = (Long) request.getAttribute("idUsuario");

		return ResponseEntity.status(HttpStatus.OK).body(feedService.buscarSolicitacoes(idPet,tutorId, localizacaoAtual));
	}
}
