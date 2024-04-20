package br.com.dogmatch.apiprincipal.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosCompletosPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosIniciasPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.DadosPet;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosDetalhamentoFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Foto.DadosFoto;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosDetalhamentoPedigree;
import br.com.dogmatch.apiprincipal.DTO.Pet.Pedigree.DadosPedigree;
import br.com.dogmatch.apiprincipal.Entity.Foto;
import br.com.dogmatch.apiprincipal.Entity.Pedigree;
import br.com.dogmatch.apiprincipal.Entity.Pet;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Repository.FotoRepository;
import br.com.dogmatch.apiprincipal.Repository.PedigreeRepository;
import br.com.dogmatch.apiprincipal.Repository.PetRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.infra.Exception.NotFoundException;
import br.com.dogmatch.apiprincipal.infra.utils.CalcularIdade;

@Service
public class PetService {

//	@Autowired
//	private ArmazenarFotoService armazenarFotoService;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private PedigreeRepository pedigreeRepository;

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	public CalcularIdade calcularIdade;

	public String cadastrar(Long tutorId, DadosPet dados) {
//		String linkFotoDePerfil = armazenarFotoService.armzenar(dados.foto(), dados.nome() + LocalDateTime.now());
//		String linkCarteirinhaDeVacina = armazenarFotoService.armzenar(dados.foto(), dados.nome() + LocalDateTime.now());

		if (calcularIdade.calcularIdade(dados.dataDeNascimento()) < 1 && dados.interesse().equals("relacionamento")) {
			throw new NotFoundException("O Pet deve ter mais de 1 ano para ter relacionamentos");
		}
		
		Optional<Tutor> tutor = tutorRepository.findById(tutorId);
		if (tutor.isEmpty()) {
			throw new NotFoundException("Tutor não encontrado");
		}

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
		pet.setTutor(tutor.get());
		pet.setStatus(true);

		petRepository.save(pet);

		return "Cadastro Concluido com Sucesso!";
	}

	public String cadastrarPedigree(DadosPedigree dados) {
		if (pedigreeRepository.findByPetId(dados.idPet()) != null) {
			throw new NotFoundException("Pet já possui pedigree cadastrado");
		}

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

		if (pedigree == null) {
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

		if (pedigree == null) {
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

	public List<DadosCompletosPet> buscarDadadosIniciais(Long idTutor) {
		List<Pet> pets = petRepository.getByTutorId(idTutor);
		List<DadosCompletosPet> dadosIniciaisPets = new ArrayList<>();

		for (Pet pet : pets) {
			DadosCompletosPet dadosCompletosPet = buscarDadosPet(pet.getId());
			dadosIniciaisPets.add(dadosCompletosPet);
		}

		return dadosIniciaisPets;
	}

	public DadosCompletosPet buscarDadosPet(Long idPet) {
		Optional<Pet> pet = petRepository.findById(idPet);

		if (pet.isEmpty()) {
			throw new NotFoundException("Pet não encontrado");
		}

		DadosIniciasPet dadosIniciaisPet = new DadosIniciasPet(pet.get());
		DadosDetalhamentoPedigree dadosPedigree = buscarPedigree(idPet);
		List<DadosDetalhamentoFoto> dadosFotos = buscarFotos(idPet);

		return new DadosCompletosPet(dadosIniciaisPet, dadosPedigree, dadosFotos);
	}

	public List<String> buscarRacas() {
		return petRepository.findAllRacas();
	}
}
