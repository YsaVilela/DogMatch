package br.com.dogmatch.apiprincipal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.Pet.DadosIniciasCompletosPet;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosAtualizarTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosDetalhamentoTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosIniciaisTutor;
import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Entity.Endereco;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Entity.Usuario;
import br.com.dogmatch.apiprincipal.Repository.EnderecoRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.Repository.UsuarioRepository;
import br.com.dogmatch.apiprincipal.Service.validacoes.tutor.ValidadorTutor;
import br.com.dogmatch.apiprincipal.infra.security.TokenService;
import jakarta.validation.Valid;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PetService petService;

	@Autowired
	private List<ValidadorTutor> validadoresTutor;

	public String cadastrarTutor(DadosTutor dados) {
		validadoresTutor.forEach(r -> r.validar(dados.cpf(), dados.email(), dados.dataDeNascimento(), null));

		Endereco endereco = new Endereco();
		endereco.setCep(dados.endereco().cep());
		endereco.setLogradouro(dados.endereco().logradouro());
		endereco.setNumero(dados.endereco().numero());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setBairro(dados.endereco().bairro());
		endereco.setCidade(dados.endereco().cidade());
		endereco.setUf(dados.endereco().uf());

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

		Usuario usuario = new Usuario();
		usuario.setLogin(dados.email());
		usuario.setSenha(passwordEncoder.encode(dados.senha()));
		usuario.setTutor(tutorRepository.getReferenceById(tutor.getId()));
		usuarioRepository.save(usuario);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(),
				dados.senha());
		var authentication = manager.authenticate(authenticationToken);

		return tokenService.gerarToken((Usuario) authentication.getPrincipal());
	}

	public DadosIniciaisTutor buscarDadosInicias(Long tutorId) {
		Tutor tutor = tutorRepository.getReferenceById(tutorId);
		DadosDetalhamentoTutor dadosDetalhamentoTutor = new DadosDetalhamentoTutor(tutor);
		List<DadosIniciasCompletosPet> pets = petService.buscarDadadosIniciais(tutorId);

		return new DadosIniciaisTutor(dadosDetalhamentoTutor, pets);
	}

	public String atualizarTutor(Long tutorId, @Valid DadosAtualizarTutor dados) {
		validadoresTutor.forEach(r -> r.validar(dados.cpf(), dados.email(), dados.dataDeNascimento(), tutorId));

		Tutor tutor = tutorRepository.getReferenceById(tutorId);

		Endereco endereco = enderecoRepository.getReferenceById(tutor.getEndereco().getId());
		endereco.setCep(dados.endereco().cep());
		endereco.setLogradouro(dados.endereco().logradouro());
		endereco.setNumero(dados.endereco().numero());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setBairro(dados.endereco().bairro());
		endereco.setCidade(dados.endereco().cidade());
		endereco.setUf(dados.endereco().uf());

		enderecoRepository.save(endereco);

		tutor.setNome(dados.nome());
		tutor.setSobrenome(dados.sobrenome());
		tutor.setDataDeNascimento(dados.dataDeNascimento());
		tutor.setGenero(dados.genero());
		tutor.setCpf(dados.cpf());
		tutor.setTelefone(dados.telefone());
		tutor.setEndereco(enderecoRepository.getReferenceById(endereco.getId()));
		tutor.setStatus(true);

		tutorRepository.save(tutor);
		
		Usuario usuario = usuarioRepository.findByTutorId(tutorId);
		usuario.setLogin(dados.email());
		usuarioRepository.save(usuario);

		return "Tutor Atualizado com Sucesso!";
	}

	public String desativarTutor(Long tutorId) {
		Tutor tutor = tutorRepository.getReferenceById(tutorId);

		petService.desativarTodosPetsPorTutor(tutorId);
		tutor.setStatus(false);
		tutorRepository.save(tutor);

		return "Tutor Desativado com Sucesso!";
	}
	
	public String ativarTutor(Long tutorId) {
		Tutor tutor = tutorRepository.getReferenceById(tutorId); 

		petService.ativarTodosPetsPorTutor(tutorId);
		tutor.setStatus(true);
		tutorRepository.save(tutor);

		return "Tutor Desativado com Sucesso!";
	}
	

	

}
