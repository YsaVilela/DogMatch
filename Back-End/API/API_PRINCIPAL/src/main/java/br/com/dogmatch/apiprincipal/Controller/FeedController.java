package br.com.dogmatch.apiprincipal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.Service.PetService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("feed")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FeedController {
	
    @Autowired
    public PetService petService;
   
	@GetMapping("/{idPetBuscado}")
	public ResponseEntity<DadosFeedPet> buscar(HttpServletRequest request, @PathVariable Long idPetBuscado, @RequestParam String localizacaoAtual) {
		Long tutorId = (Long) request.getAttribute("idUsuario");		

		return ResponseEntity.status(HttpStatus.OK).body(petService.buscarPetFeed(tutorId, idPetBuscado, localizacaoAtual));
	}
}
