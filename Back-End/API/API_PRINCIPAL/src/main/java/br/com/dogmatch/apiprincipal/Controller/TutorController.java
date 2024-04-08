package br.com.dogmatch.apiprincipal.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosDetalhamentoTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Service.TutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("tutor")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TutorController {
	@Autowired
	private TutorService tutorService;
	
	@PostMapping("cadastrar")
	public ResponseEntity<Optional<DadosDetalhamentoTutor>> cadastrar(@RequestBody @Valid DadosTutor dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tutorService.cadastrarTutor(dados));
	}
}
