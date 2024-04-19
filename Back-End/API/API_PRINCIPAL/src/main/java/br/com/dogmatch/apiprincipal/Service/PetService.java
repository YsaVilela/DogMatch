package br.com.dogmatch.apiprincipal.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosFeedPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosIniciasCompletosPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosIniciasPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosDetalhamentoFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosDetalhamentoPedigree;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosPedigree;
import br.com.dogmatch.apiprincipal.Entity.Foto;
import br.com.dogmatch.apiprincipal.Entity.Pedigree;
import br.com.dogmatch.apiprincipal.Entity.Pet;
import br.com.dogmatch.apiprincipal.Repository.FotoRepository;
import br.com.dogmatch.apiprincipal.Repository.PedigreeRepository;
import br.com.dogmatch.apiprincipal.Repository.PetRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.infra.Exception.NotFoundException;
import br.com.dogmatch.apiprincipal.infra.utils.CalcularDistancia;
import jakarta.validation.Valid;

@Service
public class PetService {

	@Autowired
	private ArmazenarFotoService armazenarFotoService;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private PedigreeRepository pedigreeRepository;

	@Autowired
	private FotoRepository fotoRepository;

	@Autowired
	public CalcularDistancia calcularDistancia;

	public String cadastrar(DadosPet dados) {
//		String linkFotoDePerfil = armazenarFotoService.armzenar(dados.foto(), dados.nome() + LocalDateTime.now());
//		String linkCarteirinhaDeVacina = armazenarFotoService.armzenar(dados.foto(), dados.nome() + LocalDateTime.now());

		Pet pet = new Pet();
		pet.setNome(dados.nome());
		pet.setSobrenome(dados.sobrenome());
		pet.setDataDeNascimento(dados.dataDeNascimento());
		pet.setGenero(dados.genero());
		pet.setRaca(dados.raca());
		pet.setCor(dados.cor());
		pet.setPorte(dados.porte());
		pet.setCarteiraDeVacinacao("linkFotoDePerfil");
		pet.setFotoDePerfil("linkCarteirinhaDeVacina");
		pet.setDescricao(dados.descricao());
		pet.setInteresse(dados.interesse());
		pet.setTutor(tutorRepository.getReferenceById(dados.idTutor()));
		pet.setStatus(true);

		petRepository.save(pet);

		return "Cadastro Concluido com Sucesso!";
	}

	public String cadastrarPedigree(DadosPedigree dados) {
		Pet pet = petRepository.getReferenceById(dados.idPet());
//		String linkPedigree = armazenarFotoService.armzenar(dados.foto(), pet.getNome() + LocalDateTime.now());

		Pedigree pedigree = new Pedigree();
		pedigree.setRg(dados.rg());
		pedigree.setDataDeEmissao(dados.dataDeEmissao());
		pedigree.setFotoPedigree("linkPedigree");
		pedigree.setPet(pet);

		pedigreeRepository.save(pedigree);

		return "Pedigree Cadastrado com Sucesso!";
	}

	public DadosDetalhamentoPedigree buscarPedigree(Long idPet) {
		Pedigree pedigree = pedigreeRepository.findByPetId(idPet);

		if (pedigree != null) {
			return new DadosDetalhamentoPedigree(pedigree);
		}

		return null;
	}
	
	public String atualizarPedigree(DadosPedigree dados) {
		Pedigree pedigree = pedigreeRepository.findByPetId(dados.idPet());

		if(pedigree == null) {
			throw new NotFoundException("Pedigree não encontrado");
		}
		
//		String linkPedigree = armazenarFotoService.armzenar(dados.foto(), pet.getNome() + LocalDateTime.now());
		pedigree.setRg(dados.rg());
		pedigree.setDataDeEmissao(dados.dataDeEmissao());
		pedigree.setFotoPedigree("linkPedigree");

		pedigreeRepository.save(pedigree);

		return "Pedigree Atualizado com Sucesso!";
	}
	
	public String deletarPedigree(Long idPet) {
		Pedigree pedigree = pedigreeRepository.findByPetId(idPet);
		
		if(pedigree == null) {
			throw new NotFoundException("Pedigree não encontrado");
		}
		
		pedigreeRepository.delete(pedigree);

		return "Pedigree Deletado com Sucesso!";
	}

	public String cadastrarFoto(DadosFoto dados) {
		Pet pet = petRepository.getReferenceById(dados.idPet());
//		String linkPedigree = armazenarFotoService.armzenar(dados.foto(), pet.getNome() + LocalDateTime.now());

		Foto foto = new Foto();
		foto.setFoto("Link foto");
		foto.setLegenda(dados.legenda());
		foto.setDataDePublicacao(dados.dataPublicacao());
		foto.setPet(pet);

		fotoRepository.save(foto);

		return "Foto Cadastrada com Sucesso!";
	}

	public List<DadosDetalhamentoFoto> buscarFotos(Long idPet) {
		List<Foto> fotos = fotoRepository.findByPetId(idPet);
		List<DadosDetalhamentoFoto> dadosFotos = new ArrayList<>();

		if (!fotos.isEmpty()) {
			for (Foto foto : fotos) {
				DadosDetalhamentoFoto dadosDetalhamentoFoto = new DadosDetalhamentoFoto(foto);
				dadosFotos.add(dadosDetalhamentoFoto);
			}
		}
		return dadosFotos;
	}
		
	public String deletarFoto(Long idFoto) {
		fotoRepository.deleteById(idFoto);

		return "Foto Deletada com Sucesso!";
	}
	
	public String atualizarPet(Long idPet, DadosPet dados) {
		Pet pet = petRepository.getReferenceById(idPet);
		
		pet.setNome(dados.nome());
		pet.setSobrenome(dados.sobrenome());
		pet.setDataDeNascimento(dados.dataDeNascimento());
		pet.setGenero(dados.genero());
		pet.setRaca(dados.raca());
		pet.setCor(dados.cor());
		pet.setPorte(dados.porte());
		pet.setCarteiraDeVacinacao("linkFotoDePerfil");
		pet.setFotoDePerfil("linkCarteirinhaDeVacina");
		pet.setDescricao(dados.descricao());
		pet.setInteresse(dados.interesse());

		petRepository.save(pet);

		return "Pet Atualizado com Sucesso!";
	}

	public String ativarPet(Long idPet) {
		Pet pet = petRepository.getReferenceById(idPet);
		pet.setStatus(true);
		petRepository.save(pet);

		return "Pet Ativado com Sucesso!";
	}
	
	public String desativarPet(Long idPet) {
		Pet pet = petRepository.getReferenceById(idPet);
		pet.setStatus(false);
		petRepository.save(pet);

		return "Pet Desativado com Sucesso!";
	}
	
	public void desativarTodosPetsPorTutor(Long idTutor) {
		List<Pet> pets = petRepository.getByTutorId(idTutor);
		for (Pet pet : pets) {
			desativarPet(pet.getId());
		}
	}

	public void ativarTodosPetsPorTutor(Long idTutor) {
		List<Pet> pets = petRepository.getByTutorId(idTutor);
		for (Pet pet : pets) {
			ativarPet(pet.getId());
		}
	}

	public List<DadosIniciasCompletosPet> buscarDadadosIniciais(Long idTutor) {
		List<Pet> pets = petRepository.getByTutorId(idTutor);
		List<DadosIniciasCompletosPet> dadosIniciaisPets = new ArrayList<>();

		for (Pet pet : pets) {
			DadosIniciasPet dadosIniciaisPet = new DadosIniciasPet(pet);
			DadosDetalhamentoPedigree dadosPedigree = buscarPedigree(pet.getId());
			List<DadosDetalhamentoFoto> dadosFotos = buscarFotos(pet.getId());

			DadosIniciasCompletosPet dadosCompletosPet = new DadosIniciasCompletosPet(dadosIniciaisPet, dadosPedigree,
					dadosFotos);
			dadosIniciaisPets.add(dadosCompletosPet);
		}

		return dadosIniciaisPets;
	}

	public DadosFeedPet buscarPetFeed(Long idPet, Long idPetBuscado, String localizacaoAtual) {
		Pet petBuscado = petRepository.getReferenceById(idPetBuscado);
		Pedigree pedigree = pedigreeRepository.findByPetId(idPetBuscado);

		boolean possuiPedigree = pedigree != null;

		Pet pet = petRepository.getReferenceById(idPet);
		String localizacao;

		if (!localizacaoAtual.isBlank()) {
			localizacao = localizacaoAtual;
		} else {
			localizacao = pet.getTutor().getEndereco().getCep();
		}

		String distancia = calcularDistancia.calcularDistanciaEntreCEPs(localizacao,
				petBuscado.getTutor().getEndereco().getCep());

		return new DadosFeedPet(petBuscado, possuiPedigree, distancia);
	}
	
}
