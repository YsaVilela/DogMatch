package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Recusa;
import jakarta.transaction.Transactional;

public interface RecusaRepository extends JpaRepository<Recusa, Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_RECUSA; ALTER SEQUENCE TB_RECUSA_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	Recusa findByPetRecusaIdAndPetRecusadoId(Long idPet, Long id);
}
