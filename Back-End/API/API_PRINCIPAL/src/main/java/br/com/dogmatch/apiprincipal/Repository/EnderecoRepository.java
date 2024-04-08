package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Endereco;
import jakarta.transaction.Transactional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_ENDERECO; ALTER SEQUENCE TB_ENDERECO_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();
}
