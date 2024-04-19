package br.com.dogmatch.apiprincipal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Mensagem;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosAtualizarTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosIniciaisTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Service.TutorService;
import br.com.dogmatch.apiprincipal.infra.security.DadosTokenJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tutor")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TutorController {
	@Autowired
	private TutorService tutorService;

	@PostMapping("cadastrar")
	public ResponseEntity<DadosTokenJWT> cadastrar(@RequestBody @Valid DadosTutor dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new DadosTokenJWT(tutorService.cadastrarTutor(dados)));
	}
	
	@GetMapping("dados")
	public ResponseEntity<DadosIniciaisTutor> buscarDados(HttpServletRequest request) {
		Long tutorId = (Long) request.getAttribute("idUsuario");		

		return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.buscarDadosInicias(tutorId));
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<Mensagem> atualizarDados(HttpServletRequest request, @RequestBody @Valid DadosAtualizarTutor dados) {
		Long tutorId = (Long) request.getAttribute("idUsuario");		

		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(tutorService.atualizarTutor(tutorId, dados)));
	}
	
	@PutMapping("desativar")
	public ResponseEntity<Mensagem> desativarTutor(HttpServletRequest request) {
		Long tutorId = (Long) request.getAttribute("idUsuario");		

		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(tutorService.desativarTutor(tutorId)));
	}
	
	@PutMapping("ativar")
	public ResponseEntity<Mensagem> ativarTutor(HttpServletRequest request) {
		Long tutorId = (Long) request.getAttribute("idUsuario");		

		return ResponseEntity.status(HttpStatus.OK).body(new Mensagem(tutorService.ativarTutor(tutorId)));
	}

}
