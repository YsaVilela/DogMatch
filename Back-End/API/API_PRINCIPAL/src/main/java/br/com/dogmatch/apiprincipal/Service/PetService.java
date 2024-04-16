package br.com.dogmatch.apiprincipal.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosPet;
import br.com.dogmatch.apiprincipal.Entity.Pet;
import br.com.dogmatch.apiprincipal.Repository.PetRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.dev.PhotoConverter;

@Service
public class PetService {

	@Autowired
	private ArmazenarFotoService armazenarFotoService;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private PetRepository petRepository;

	// temporário
	@Autowired
	private PhotoConverter photoConverter;

	public String cadastrar(DadosPet dados) {
		MultipartFile foto = photoConverter.converter();
		String linkFotoDePerfil = armazenarFotoService.armzenar(foto, dados.nome() + LocalDateTime.now());
		String linkCarteirinhaDeVacina = armazenarFotoService.armzenar(foto, dados.nome() + LocalDateTime.now());

		Pet pet = new Pet();
		pet.setNome(dados.nome());
		pet.setSobrenome(dados.sobrenome());
		pet.setDataDeNascimento(dados.dataDeNascimento());
		pet.setGenero(dados.genero());
		pet.setRaca(dados.raca());
		pet.setCor(dados.cor());
		pet.setPorte(dados.porte());
		pet.setCarteiraDeVacinacao(linkCarteirinhaDeVacina);
		pet.setFotoDePerfil(linkFotoDePerfil);
		pet.setDescricao(dados.descricao());
		pet.setInteresse(dados.interesse());
		pet.setTutor(tutorRepository.getReferenceById(dados.idTutor()));
		pet.setStatus(true);

		petRepository.save(pet);

		return "Cadastro Concluido com Sucesso!";
	}
}
