package br.com.dogmatch.apiprincipal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dogmatch.apiprincipal.DTO.Tutor.DadosTutor;
import br.com.dogmatch.apiprincipal.Entity.Endereco;
import br.com.dogmatch.apiprincipal.Entity.Tutor;
import br.com.dogmatch.apiprincipal.Entity.Usuario;
import br.com.dogmatch.apiprincipal.Repository.EnderecoRepository;
import br.com.dogmatch.apiprincipal.Repository.TutorRepository;
import br.com.dogmatch.apiprincipal.Repository.UsuarioRepository;
import br.com.dogmatch.apiprincipal.Service.validacoes.tutor.ValidadorTutor;
import br.com.dogmatch.apiprincipal.infra.security.TokenService;

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
	private List<ValidadorTutor> validadoresTutor;
	
	public String cadastrarTutor(DadosTutor dados) {
		validadoresTutor.forEach(r -> r.validar(dados));
		
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
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		return tokenJWT;
	}
	
}
