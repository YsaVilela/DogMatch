package br.com.dogmatch.apidominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apidominio.DTO.DadosCidade;
import br.com.dogmatch.apidominio.repository.CidadeRepository;


@RestController
@RequestMapping ("cidades")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CidadesController {
	
	@Autowired
	private CidadeRepository repository;
	
	@GetMapping ("/{siglaEstado}")
	public ResponseEntity<List<DadosCidade>> listarCidadePorEstado ( @PathVariable String siglaEstado) {
		List<DadosCidade> listagemDeCidades = repository.findByNomeEstado(siglaEstado);
		return ResponseEntity.ok(listagemDeCidades);
	} 
} 