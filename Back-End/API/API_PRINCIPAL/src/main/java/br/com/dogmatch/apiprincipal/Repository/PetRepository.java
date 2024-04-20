package br.com.dogmatch.apiprincipal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.dogmatch.apiprincipal.Entity.Pet;
import jakarta.transaction.Transactional;

public interface PetRepository extends JpaRepository<Pet, Long> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TB_PET; ALTER SEQUENCE TB_PET_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();


	List<Pet> getByTutorId(Long idTutor);

	@Query(value = "SELECT DISTINCT p.raca FROM Pet p")
	List<String> findAllRacas();


	List<Pet> findByStatusTrue();
}
