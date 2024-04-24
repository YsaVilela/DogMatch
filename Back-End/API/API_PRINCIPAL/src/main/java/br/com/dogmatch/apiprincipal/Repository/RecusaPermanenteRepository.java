package br.com.dogmatch.apiprincipal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.RecusaPermanente;
import jakarta.transaction.Transactional;

public interface RecusaPermanenteRepository extends JpaRepository<RecusaPermanente, Long>{
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_RECUSA_PERMANENTE; ALTER SEQUENCE TB_RECUSA_PERMANENTE_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

	@Query("SELECT pr FROM RecusaPermanente pr WHERE (pr.petRecusa.id = :idPet AND pr.petRecusado.id = :id) OR (pr.petRecusado.id = :idPet AND pr.petRecusa.id = :id)")
	RecusaPermanente findRelatedPets(Long idPet, Long id);
}
