package br.com.dogmatch.apiprincipal.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.DetalhamentoSolicitacao;
import br.com.dogmatch.apiprincipal.DTO.FiltroFeed;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.Entity.Match;
import br.com.dogmatch.apiprincipal.Entity.Pet;
import br.com.dogmatch.apiprincipal.Entity.Recusa;
import br.com.dogmatch.apiprincipal.Entity.RecusaPermanente;
import br.com.dogmatch.apiprincipal.Entity.Solicitacao;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Repository.RecusaRepository;
import br.com.dogmatch.apiprincipal.Repository.SolicitacaoRepository;
import br.com.dogmatch.apiprincipal.Repository.MatchRepository;
import br.com.dogmatch.apiprincipal.Repository.PedigreeRepository;
import br.com.dogmatch.apiprincipal.Repository.PetRepository;
import br.com.dogmatch.apiprincipal.Repository.RecusaPermanenteRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.infra.Exception.NotFoundException;
import br.com.dogmatch.apiprincipal.infra.Exception.ProcessInvalidException;
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
	private RecusaRepository recusaRepository;

	@Autowired
	private RecusaPermanenteRepository recusapermanenteRepository;

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private PedigreeRepository pedigreeRepository;

	public List<DadosFeedPet> buscarPetFeed(FiltroFeed filtros) {
		Optional<Pet> petTitular = petRepository.findById(filtros.idPet());
		if (petTitular.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Tutor tutor = tutorRepository.getReferenceById(filtros.tutorId());

		if (petTitular.get().getTutor() != tutor) {
			throw new NotFoundException("Esse pet não pertence ao tutor atual.");
		}

		String localizacao;
		if (filtros.localizacaoAtual() != null) {
			localizacao = filtros.localizacaoAtual();
		} else {
			localizacao = tutor.getEndereco().getCep();
		}

		List<Pet> pets = petRepository.findByStatusTrue();

		if (petTitular.get().getInteresse().equals("relacionamento")) {
			pets = pets.stream().filter(pet -> !pet.getGenero().equals(petTitular.get().getGenero())).toList();
		}

		List<DadosFeedPet> dadosPets;

		dadosPets = pets.stream().filter(pet -> !pet.getTutor().getId().equals(filtros.tutorId()))
				.filter(pet -> pet.getInteresse().equals(petTitular.get().getInteresse()))
				.filter(pet -> recusaRepository.findByPetRecusaIdAndPetRecusadoId(filtros.idPet(), pet.getId()) == null)
				.filter(pet -> matchRepository.findRelatedPets(filtros.idPet(), pet.getId()) == null)
				.filter(pet -> recusapermanenteRepository.findRelatedPets(filtros.idPet(), pet.getId()) == null)
				.filter(pet -> solicitacaoRepository
						.findByPetSolicitanteIdAndPetSolicitadoId(filtros.idPet(), pet.getId()).isEmpty())
				.filter(pet -> {
					int idade = calcularIdade.calcularIdade(pet.getDataDeNascimento());
					return filtros.idadeMinima() <= idade && idade <= filtros.idadeMaxima();
				}).filter(pet -> !filtros.devePossuirPedigree() || pedigreeRepository.findByPetId(pet.getId()) != null)
				.filter(pet -> !filtros.deveSerCastrado() || pet.getCastrado()).filter(pet -> {
					Long distancia = calcularDistancia.calcularDistanciaEntreCEPs(localizacao,
							pet.getTutor().getEndereco().getCep());
					return distancia != -1 && filtros.distanciaMaxima() == null
							|| distancia <= filtros.distanciaMaxima();
				}).filter(pet -> filtros.raca() == null || filtros.raca().equals(pet.getRaca()))
				.filter(pet -> filtros.porte() == null || filtros.porte().equals(pet.getPorte())).map(pet -> {
					boolean possuiPedigree = pedigreeRepository.findByPetId(pet.getId()) != null;
					return new DadosFeedPet(pet, possuiPedigree, calcularDistancia
							.calcularDistanciaEntreCEPs(localizacao, pet.getTutor().getEndereco().getCep()));
				}).toList();

		return dadosPets;
	}

	public String recusarPet(Long tutorId, Long idPet, Long idPetRecusado) {

		Optional<Pet> petTitular = petRepository.findById(idPet);
		Optional<Pet> petRecusado = petRepository.findById(idPetRecusado);

		if (petTitular.isEmpty() || petRecusado.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Tutor tutor = tutorRepository.getReferenceById(tutorId);

		if (petTitular.get().getTutor() != tutor) {
			throw new NotFoundException("Esse pet não pertence ao tutor atual.");
		}

		if (solicitacaoRepository.findByPetSolicitanteIdAndPetSolicitadoId(idPet, idPetRecusado).isPresent()) {
			Optional<Solicitacao> solicitacao = solicitacaoRepository.findByPetSolicitanteIdAndPetSolicitadoId(idPet,
					idPetRecusado);
			solicitacaoRepository.delete(solicitacao.get());
		}

		if (recusaRepository.findByPetRecusaIdAndPetRecusadoId(idPet, idPetRecusado) != null) {
			throw new ProcessInvalidException("Você já recusou esse pet");
		}

		if (recusaRepository.findByPetRecusaIdAndPetRecusadoId(idPetRecusado, idPet) != null) {
			recusaRepository.delete(recusaRepository.findByPetRecusaIdAndPetRecusadoId(idPetRecusado, idPet));
			criarRecusaPermanente(idPet, idPetRecusado);
			return "Recusa Salva com Sucesso";
		}

		if (matchRepository.findRelatedPets(idPet, idPetRecusado) != null) {
			throw new ProcessInvalidException("Você já deu match com esse pet");
		}
		
		if(recusapermanenteRepository.findRelatedPets(idPet, idPetRecusado) != null) {
			throw new ProcessInvalidException("Você já recusou esse pet");
		}

		Recusa recusa = new Recusa();

		recusa.setPetRecusa(petTitular.get());
		recusa.setPetRecusado(petRecusado.get());

		recusaRepository.save(recusa);

		return "Recusa Salva com Sucesso";
	}

	public String solicitarPet(Long tutorId, Long idPet, Long idPetSolicitado) {
		Optional<Pet> petTitular = petRepository.findById(idPet);
		Optional<Pet> petSolicitado = petRepository.findById(idPetSolicitado);

		if (petTitular.isEmpty() || petSolicitado.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Tutor tutor = tutorRepository.getReferenceById(tutorId);

		if (petTitular.get().getTutor() != tutor) {
			throw new NotFoundException("Esse pet não pertence ao tutor atual.");
		}
		
		if (recusaRepository.findByPetRecusaIdAndPetRecusadoId(idPet, idPetSolicitado) != null) {
			throw new ProcessInvalidException("Você já recusou esse pet");
		}
		
		if (solicitacaoRepository.findByPetSolicitanteIdAndPetSolicitadoId(idPet, idPetSolicitado).isPresent()) {
			throw new ProcessInvalidException("Você já solicitou esse pet");
		}
		
		if(recusapermanenteRepository.findRelatedPets(idPet, idPetSolicitado) != null) {
			throw new ProcessInvalidException("Você já recusou esse pet");
		}

		if (solicitacaoRepository.findByPetSolicitanteIdAndPetSolicitadoId(idPetSolicitado, idPet).isPresent()) {
			Optional<Solicitacao> solicitacao = solicitacaoRepository
					.findByPetSolicitanteIdAndPetSolicitadoId(idPetSolicitado, idPet);
			solicitacaoRepository.delete(solicitacao.get());
			criarMatch(idPet, idPetSolicitado);
			return "Dog Match";
		}

		Solicitacao solicitacao = new Solicitacao();

		solicitacao.setPetSolicitante(petTitular.get());
		solicitacao.setPetSolicitado(petSolicitado.get());
		solicitacao.setStatus("Aguardando");
		solicitacao.setDataDeSolicitacao(LocalDateTime.now());

		solicitacaoRepository.save(solicitacao);

		return "Solicitação Salva com Sucesso";
	}

	public String aceitarSolicitacaoPet(Long idPet, Long idPetSolicitante) {
		Optional<Pet> petTitular = petRepository.findById(idPet);
		Optional<Pet> petSolicitante = petRepository.findById(idPetSolicitante);
		if (petTitular.isEmpty() || petSolicitante.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Optional<Solicitacao> solicitacao = solicitacaoRepository
				.findByPetSolicitanteIdAndPetSolicitadoId(idPetSolicitante, idPet);
		if (solicitacao.isEmpty()) {
			throw new NotFoundException("Solicitação não encontrada");
		}

		criarMatch(idPet, idPetSolicitante);
		solicitacao.get().setStatus("Aceita");

		solicitacaoRepository.save(solicitacao.get());

		return "Dog Match";
	}

	public String recusarSolicitacaoPet(Long idPet, Long idPetSolicitante) {
		Optional<Pet> petTitular = petRepository.findById(idPet);
		Optional<Pet> petSolicitante = petRepository.findById(idPetSolicitante);
		if (petTitular.isEmpty() || petSolicitante.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Optional<Solicitacao> solicitacao = solicitacaoRepository
				.findByPetSolicitanteIdAndPetSolicitadoId(idPetSolicitante, idPet);
		if (solicitacao.isEmpty()) {
			throw new NotFoundException("Solicitação não encontrada");
		}

		criarRecusaPermanente(idPet, idPetSolicitante);
		solicitacao.get().setStatus("Recusada");

		solicitacaoRepository.save(solicitacao.get());

		return "Solictação Recusada Com Sucesso";
	}

	public String criarMatch(Long idPetTitular, Long idPetSolicitado) {
		Optional<Pet> petTitular = petRepository.findById(idPetTitular);
		Optional<Pet> petSolicitado = petRepository.findById(idPetSolicitado);

		if (petTitular.isEmpty() || petSolicitado.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Match match = new Match();
		match.setPet1(petTitular.get());
		match.setPet2(petSolicitado.get());

		matchRepository.save(match);
		return "Match Criado com Sucesso";
	}

	public String criarRecusaPermanente(Long idPet, Long idPetSolicitante) {
		Optional<Pet> petTitular = petRepository.findById(idPet);
		Optional<Pet> petSolicitante = petRepository.findById(idPetSolicitante);

		RecusaPermanente recusaPermanente = new RecusaPermanente();
		recusaPermanente.setPetRecusa(petTitular.get());
		recusaPermanente.setPetRecusado(petSolicitante.get());

		recusapermanenteRepository.save(recusaPermanente);

		return "Recusa Permanente Criado com Sucesso";
	}

	public List<DetalhamentoSolicitacao> buscarSolicitacoes(Long idPet, Long tutorId, String localizacaoAtual) {
		Optional<Pet> pet = petRepository.findById(idPet);
		if (pet.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		Tutor tutor = tutorRepository.getReferenceById(tutorId);

		if (pet.get().getTutor() != tutor) {
			throw new NotFoundException("Esse pet não pertence ao tutor atual.");
		}

		String localizacao;
		if (localizacaoAtual != null) {
			localizacao = localizacaoAtual;
		} else {
			localizacao = tutor.getEndereco().getCep();
		}

		List<DetalhamentoSolicitacao> dadosSolicitacaoes = new ArrayList<>();
		List<Solicitacao> solicitacoes = solicitacaoRepository.findByPetSolicitadoId(idPet);
		for (Solicitacao solicitacao : solicitacoes) {
			Pet petSolicitante = solicitacao.getPetSolicitante();
			boolean possuiPedigree = pedigreeRepository.findByPetId(petSolicitante.getId()) != null;
			Long distancia = calcularDistancia.calcularDistanciaEntreCEPs(localizacao,
					petSolicitante.getTutor().getEndereco().getCep());

			DadosFeedPet dadosPetSolicitante = new DadosFeedPet(petSolicitante, possuiPedigree, distancia);

			DetalhamentoSolicitacao dadosSolicitacao = new DetalhamentoSolicitacao(solicitacao, dadosPetSolicitante);
			dadosSolicitacaoes.add(dadosSolicitacao);
		}

		return dadosSolicitacaoes;
	}

}
