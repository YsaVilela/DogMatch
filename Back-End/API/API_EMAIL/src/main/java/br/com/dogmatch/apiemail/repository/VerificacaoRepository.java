package br.com.dogmatch.apiemail.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dogmatch.apiemail.entity.Verificacao;

public interface VerificacaoRepository extends MongoRepository<Verificacao, String>{

	Optional<Verificacao> findByEmailAndCodigo(String email, int codigo);

	Optional<Verificacao> findByEmail(String email);

}
