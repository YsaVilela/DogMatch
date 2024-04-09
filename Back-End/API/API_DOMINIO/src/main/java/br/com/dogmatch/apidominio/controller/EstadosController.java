package br.com.dogmatch.apidominio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apidominio.DTO.DadosEstados;
import br.com.dogmatch.apidominio.entity.Estados;
import br.com.dogmatch.apidominio.repository.EstadoRepository;


@RestController
@RequestMapping ("estados")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EstadosController {
	
	@Autowired
	private EstadoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<DadosEstados>> listar() {
	    List<Estados> estadosList = repository.findAll();
	    List<DadosEstados> dadosEstadosList = new ArrayList<>();

	    for (Estados estado : estadosList) {
	        DadosEstados dadosEstado = new DadosEstados(estado); 
	        dadosEstadosList.add(dadosEstado);
	    }

	    return ResponseEntity.ok(dadosEstadosList);
	}
} 