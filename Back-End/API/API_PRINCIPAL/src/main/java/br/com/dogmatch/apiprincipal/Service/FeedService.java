package br.com.dogmatch.apiprincipal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.FiltroFeed;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.Entity.Pet;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Repository.RecusaRepository;
import br.com.dogmatch.apiprincipal.Repository.PedigreeRepository;
import br.com.dogmatch.apiprincipal.Repository.PetRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.infra.utils.CalcularDistancia;
import br.com.dogmatch.apiprincipal.infra.utils.CalcularIdade;

@Service
public class FeedService {

	@Autowired
	public CalcularDistancia calcularDistancia;

	@Autowired
	public CalcularIdade calcularIdade;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private RecusaRepository feedRepository;

	@Autowired
	private PedigreeRepository pedigreeRepository;

	public List<DadosFeedPet> buscarPetFeed(FiltroFeed filtros) {

		Tutor tutor = tutorRepository.getReferenceById(filtros.tutorId());
		Pet petTitular = petRepository.getReferenceById(filtros.idPet());

		String localizacao;
		if (filtros.localizacaoAtual() != null) {
			localizacao = filtros.localizacaoAtual();
		} else {
			localizacao = tutor.getEndereco().getCep();
		}

		List<Pet> pets = petRepository.findByStatusTrue();

		if (petTitular.getInteresse().equals("relacionamento")) {
			pets = pets.stream().filter(pet -> !pet.getGenero().equals(petTitular.getGenero())).toList();
		}

		List<DadosFeedPet> dadosPets;

		dadosPets = pets.stream().filter(pet -> !pet.getTutor().getId().equals(filtros.tutorId()))
				.filter(pet -> pet.getInteresse().equals(petTitular.getInteresse()))
				.filter(pet -> feedRepository.findByPetRecusaIdAndPetRecusadoId(filtros.idPet(), pet.getId()) == null)
				.filter(pet -> {
					int idade = calcularIdade.calcularIdade(pet.getDataDeNascimento());
					return filtros.idadeMinima() <= idade && idade <= filtros.idadeMaxima();
				}).filter(pet -> !filtros.devePossuirPedigree() || pedigreeRepository.findByPetId(pet.getId()) != null)
				.filter(pet -> {
					Long distancia = calcularDistancia.calcularDistanciaEntreCEPs(localizacao,
							pet.getTutor().getEndereco().getCep());
					return filtros.distanciaMaxima() == null || distancia <= filtros.distanciaMaxima();
				}).filter(pet -> filtros.raca() == null || filtros.raca().equals(pet.getRaca()))
				.filter(pet -> filtros.porte() == null || filtros.porte().equals(pet.getPorte())).map(pet -> {
					boolean possuiPedigree = pedigreeRepository.findByPetId(pet.getId()) != null;
					return new DadosFeedPet(pet, possuiPedigree, calcularDistancia
							.calcularDistanciaEntreCEPs(localizacao, pet.getTutor().getEndereco().getCep()));
				}).toList();

		return dadosPets;
	}
}
