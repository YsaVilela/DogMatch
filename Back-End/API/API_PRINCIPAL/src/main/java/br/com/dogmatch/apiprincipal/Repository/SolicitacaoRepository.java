package br.com.dogmatch.apiprincipal.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Solicitacao;
import jakarta.transaction.Transactional;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_SOLICITACAO; ALTER SEQUENCE TB_SOLICITACAO_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	Optional<Solicitacao> findByPetSolicitanteIdAndPetSolicitadoId(Long idPet, Long id);

	List<Solicitacao> findByPetSolicitanteId(Long idPet);

	List<Solicitacao> findByPetSolicitadoId(Long idPet);
}
