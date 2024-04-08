package br.com.dogmatch.apiprincipal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosDetalhamentoTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Entity.Endereco;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Repository.EnderecoRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.Service.validacoes.tutor.ValidadorTutor;

@Service
public class TutorService {
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	@Autowired
	private List<ValidadorTutor> validadoresTutor;
	
	public Optional<DadosDetalhamentoTutor> cadastrarTutor(DadosTutor dados) {
		validadoresTutor.forEach(r -> r.validar(dados));
		
		Endereco endereco = new Endereco();
		endereco.setCep(dados.endereco().cep());
		endereco.setLogradouro(dados.endereco().logradouro());
		endereco.setNumero(dados.endereco().numero());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setBairro(dados.endereco().bairro());
		endereco.setCidade(dados.endereco().cidade());
		endereco.setUf(dados.endereco().UF());

		enderecoRepository.save(endereco);
		
		Tutor tutor = new Tutor();
		tutor.setNome(dados.nome());
		tutor.setSobrenome(dados.sobrenome());
		tutor.setDataDeNascimento(dados.dataDeNascimento());
		tutor.setGenero(dados.genero());
		tutor.setCpf(dados.cpf());
		tutor.setEmail(dados.email());
		tutor.setTelefone(dados.telefone());
		tutor.setEndereco(enderecoRepository.getReferenceById(endereco.getId()));
		tutor.setStatus(true);
		
		tutorRepository.save(tutor);

		return tutorRepository.findById(tutor.getId()).map(DadosDetalhamentoTutor::new);
	}
	
}
